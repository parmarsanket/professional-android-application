package com.example.firebaseauthenticationapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.isTraceInProgress
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebaseauthenticationapp.ui.theme.FirebaseAuthenticationAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private val auth: FirebaseAuth by lazy { Firebase.auth }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppContent(auth = auth)
        }
    }

    @Composable
    fun AppContent(auth: FirebaseAuth) {
        var showSplashScreen by remember { mutableStateOf(true) }

        LaunchedEffect(showSplashScreen) {
            delay(2000)
            showSplashScreen = false
        }

        Crossfade(targetState = showSplashScreen, label = "") { isSplashScreenVisible ->
            if (isSplashScreenVisible) {
                SplashScreen {
                    showSplashScreen = false
                }
            } else {
                AuthOrMainScreen(auth)
            }
        }
    }


    @Composable
    fun SplashScreen(navigateToAuthOrMainScreen: () -> Unit) {
        // Rotate effect for the image
        var rotationState by remember { mutableFloatStateOf(0f) }

        // Navigate to AuthOrMainScreen after a delay
        LaunchedEffect(true) {
            // Simulate a delay of 2 seconds
            delay(2000)
            // Call the provided lambda to navigate to AuthOrMainScreen
            navigateToAuthOrMainScreen()
        }

        // Rotation effect animation
        LaunchedEffect(rotationState) {
            while (true) {
                delay(16) // Adjust the delay to control the rotation speed
                rotationState += 1f
            }
        }

        // Splash screen UI with transitions
        val scale by animateFloatAsState(
            targetValue = 1f,
            animationSpec = TweenSpec(durationMillis = 500), label = ""
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .scale(scale)
                    .rotate(rotationState) // Apply the rotation effect
            )
        }
    }


    @Composable
    fun AuthOrMainScreen(auth: FirebaseAuth) {
        var user by remember { mutableStateOf(auth.currentUser) }

        if (user == null) {
            AuthScreen(
                onSignedIn = { signedInUser ->
                    user = signedInUser
                }
            )
        } else {
            MainScreen(
                user = user!!,  // Pass the user information to MainScreen
                onSignOut = {
                    auth.signOut()
                    user = null
                }
            )
        }
    }


    @Composable
    fun AuthScreen(onSignedIn: (FirebaseUser) -> Unit) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var isLoading by remember { mutableStateOf(false) }
        var isSignIn by remember { mutableStateOf(true) }
        var isPasswordVisible by remember { mutableStateOf(false) }
        // State variables for error message
        var myErrorMessage by remember { mutableStateOf<String?>(null) }


        // Load your image as ImageBitmap (replace R.drawable.your_image with your actual image resource)
        val imagePainter: Painter = painterResource(id = R.drawable.ic_launcher_background)

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Background Image
            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Create a transparent card with rounded corners
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.25f))
                    .padding(25.dp)
                    .clip(RoundedCornerShape(16.dp)),
                elevation = CardDefaults.cardElevation()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // First Name TextField
                    if (!isSignIn) {
                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            label = {
                                Text("First Name")
                            },
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Last Name TextField
                        TextField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            label = {
                                Text("Last Name")
                            },
                        )
                    }

                    // Email TextField
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        label = {
                            Text("Email")
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = null)
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email
                        ),
                        visualTransformation = if (isSignIn) VisualTransformation.None else VisualTransformation.None
                    )

                    // Password TextField
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        label = {
                            Text("Password")
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null)
                        },
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password
                        ),
                        trailingIcon = {
                            IconButton(
                                onClick = { isPasswordVisible = !isPasswordVisible }
                            ) {
                                val icon =
                                    if (isPasswordVisible) Icons.Default.Lock else Icons.Default.Search
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "Toggle Password Visibility"
                                )
                            }
                        }
                    )

                    // ... (other content)
                    Spacer(modifier = Modifier.height(16.dp))

                    // Error Message
                    if (myErrorMessage != null) {
                        Text(
                            text = myErrorMessage!!,
                            color = Color.Red,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Sign In/Sign Up Buttons
                    Button(
                        onClick = {
                            if (isSignIn) {
                                signIn(auth, email, password,
                                    onSignedIn = { signedInUser ->
                                        onSignedIn(signedInUser)
                                    },
                                    onSignInError = { errorMessage ->
                                        // Show toast message on sign-in error
                                        myErrorMessage = errorMessage
                                    }
                                )
                            } else {
                                signUp(auth, email, password, firstName, lastName) { signedInUser ->
                                    onSignedIn(signedInUser)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(8.dp),
                    ) {
                        Text(
                            text = if (isSignIn) "Sign In" else "Sign Up",
                            fontSize = 18.sp,
                        )
                    }


                    // Clickable Text
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(8.dp),
                    ) {
                        ClickableText(
                            text = AnnotatedString(buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.Blue)) {
                                    append(if (isSignIn) "Don't have an account? Sign Up" else "Already have an account? Sign In")
                                }
                            }.toString()),
                            onClick = {
                                myErrorMessage = null
                                email = ""
                                password = ""
                                isSignIn = !isSignIn
                            },
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }

            }
        }
    }

    // Function to handle sign-in errors
    private fun onSignInError(errorMessage: String) {
        // Handle the sign-in error as needed
        // For now, we'll print the error message
        println("Sign-in error: $errorMessage")
    }


    @Composable
    fun MainScreen(user: FirebaseUser, onSignOut: () -> Unit) {
        val userProfile = remember { mutableStateOf<User1?>(null) }

        // Fetch user profile from Firestore
        LaunchedEffect(user.uid) {
            val firestore = FirebaseFirestore.getInstance()
            val userDocRef = firestore.collection("users").document(user.uid)

            userDocRef.get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val firstName = document.getString("firstName")
                        val lastName = document.getString("lastName")

                        userProfile.value = User1(firstName, lastName, user.email ?: "")
                    } else {
                        // Handle the case where the document doesn't exist
                    }
                }
                .addOnFailureListener { e ->
                    // Handle failure

                }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            userProfile.value?.let {
                Text("Welcome, ${it.firstName} ${it.lastName}!")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onSignOut()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Sign Out")
            }
        }
    }


    private fun signIn(
        auth: FirebaseAuth,
        email: String,
        password: String,
        onSignedIn: (FirebaseUser) -> Unit,
        onSignInError: (String) -> Unit // Callback for sign-in error
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    onSignedIn(user!!)
                } else {
                    // Handle sign-in failure
                    onSignInError("Invalid email or password")
                }
            }
    }


    private fun signUp(
        auth: FirebaseAuth,
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        onSignedIn: (FirebaseUser) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val firestore = FirebaseFirestore.getInstance()

                    // Create a user profile in Firestore
                    val userProfile = hashMapOf(
                        "uid" to user!!.uid,
                        "firstName" to firstName,
                        "lastName" to lastName,
                        "email" to email
                    )


                    firestore.collection("users")
                        .document(user.uid)
                        .set(userProfile)
                        .addOnSuccessListener {
                            Log.d("Firestore", "User added")
                            onSignedIn(user)
                        }
                        .addOnFailureListener {
                            Log.e("Firestore", "Failed to add user", it)

                        }
                } else {
                    // Handle sign-up failure

                }
            }

//    @Composable
//    fun AppContent(modifier: Modifier = Modifier, auth: FirebaseAuth) {
//        var showSpanshScreen by remember { mutableStateOf(true) }
//        LaunchedEffect(showSpanshScreen) {
//            delay(2000)
//            showSpanshScreen = false
//        }
//        Crossfade(targetState = showSpanshScreen, label = "") { isvisible ->
//            if (isvisible) {
//                showSpanshScreen = false
//            } else {
//                AuthOrMainScreen(auth = auth)
//            }
//
//        }
//    }
//
//    @Composable
//    fun SplashScreen(
//        modifier: Modifier = Modifier,
//        navigateTOAuthorMain: () -> Unit,
//    ) {
//
//        var rotationState by remember { mutableFloatStateOf(0f) }
//        LaunchedEffect(true)
//        {
//            delay(2000)
//            navigateTOAuthorMain()
//        }
//        LaunchedEffect(rotationState) {
//            while (true) {
//                delay(16)
//                rotationState += 1f
//            }
//        }
//        val scale by animateFloatAsState(
//            targetValue = 1f,
//            animationSpec = TweenSpec(durationMillis = 500),
//            label = ""
//        )
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(color = Color.White),
//            contentAlignment = Alignment.Center
//        ) {
//
//            Image(
//                painter = painterResource(R.drawable.ic_launcher_foreground),
//                contentDescription = " ",
//                modifier = Modifier
//                    .size(150.dp)
//                    .clip(CircleShape)
//                    .scale(scale)
//                    .rotate(rotationState)
//            )
//
//        }
//
//
//    }
//
//    @Composable
//    fun AuthOrMainScreen(modifier: Modifier = Modifier, auth: FirebaseAuth) {
//        var user by remember { mutableStateOf(auth.currentUser) }
//
//        if (user == null) {
//            AuthScreen(
//                onSignedIn = { singnedInUser ->
//                    user = singnedInUser
//                }
//            )
//        } else {
//            MainScreen(user = user!!, onSignOut = {
//                auth.signOut()
//                user = null
//            })
//        }
//
//
//    }
//
//    @Composable
//    fun MainScreen(user: FirebaseUser, onSignOut: () -> Unit) {
//
//        val userProfile = remember { mutableStateOf<User1?>(null) }
//        LaunchedEffect(user.uid) {
//            val fireStore = FirebaseFirestore.getInstance()
//            val userDocRef = fireStore.collection("users").document(user.uid)
//
//            userDocRef.get()
//                .addOnSuccessListener { document ->
//                    if (document.exists()) {
//                        val firstName = document.getString("firstName")
//                        val lastName = document.getString("lastName")
//                        userProfile.value = User1(firstName, lastName, user.email ?: "")
//                    } else {
//
//                    }
//                }
//                .addOnFailureListener {
//
//                }
//        }
//        Column(
//            modifier = Modifier.fillMaxSize()
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            userProfile.value?.let {
//                Text(text = "welcome, ${it.firstName}${it.lastName}!")
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(onClick = { onSignOut() }, modifier = Modifier.fillMaxWidth().height(60.dp)) {
//                Text("Signout")
//            }
//        }
//
//    }
//
//    data class User1(
//        val firstName: String?,
//        val lastName: String?,
//        val email: String
//    )
//
//    @Composable
//    fun AuthScreen(onSignedIn: (FirebaseUser) -> Unit) {
//        var email by remember { mutableStateOf("") }
//        var password by remember { mutableStateOf("") }
//        var firstName by remember { mutableStateOf("") }
//        var lastName by remember { mutableStateOf("") }
//
//        var isLoading by remember { mutableStateOf(false) }
//
//        var isSignin by remember { mutableStateOf(true) }
//        var ispasswordVisible by remember { mutableStateOf(false) }
//        //state variable for error message
//
//        var myErrrormessages by remember { mutableStateOf<String?>(null) }
//
//        val imagePainter: Painter = painterResource(R.drawable.ic_launcher_background)
//
//        Box(modifier = Modifier.fillMaxSize()) {
//            Image(
//                painter = imagePainter,
//                contentDescription = "",
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.Crop
//            )
//            //Card
//            Card(
//                modifier = Modifier.fillMaxSize()
//                    .background(
//                        MaterialTheme.colorScheme.surface.copy(alpha = 0.25f)
//                    ).padding(25.dp).clip(RoundedCornerShape(16.dp)),
//                elevation = CardDefaults.cardElevation()
//
//            ) {
//                Column(
//                    modifier = Modifier.fillMaxSize().padding(16.dp),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//
//                    if (!isSignin) {
//                        Spacer(modifier = Modifier.height(8.dp))
//                        TextField(
//                            value = firstName,
//                            onValueChange = {
//                                firstName = it
//                            },
//                            modifier = Modifier.fillMaxWidth()
//                                .padding(8.dp),
//                            label = { Text("FirstName") }
//
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        TextField(
//                            value = lastName,
//                            onValueChange = {
//                                lastName = it
//                            },
//                            modifier = Modifier.fillMaxWidth()
//                                .padding(8.dp),
//                            label = { Text("LastName") }
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.height(8.dp))
//                    TextField(
//                        value = email,
//                        onValueChange = {
//                            email = it
//                        },
//                        modifier = Modifier.fillMaxWidth()
//                            .padding(8.dp),
//                        label = { Text("email") },
//                        leadingIcon = {
//                            Icon(
//                                imageVector = Icons.Default.Email, contentDescription = ""
//                            )
//                        },
//                        keyboardOptions = KeyboardOptions.Default.copy(
//                            keyboardType = KeyboardType.Email
//                        ),
//                        visualTransformation = if (isSignin) VisualTransformation.None else VisualTransformation.None
//
//
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    TextField(
//                        value = password,
//                        onValueChange = {
//                            password = it
//                        },
//                        modifier = Modifier.fillMaxWidth()
//                            .padding(8.dp),
//                        label = { Text("password") },
//                        leadingIcon = {
//                            Icon(
//                                imageVector = Icons.Default.Lock, contentDescription = ""
//                            )
//                        },
//                        keyboardOptions = KeyboardOptions.Default.copy(
//                            keyboardType = KeyboardType.Password
//                        ),
//                        visualTransformation = if (ispasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//                        trailingIcon = {
//                            IconButton(onClick = { ispasswordVisible = !ispasswordVisible }) {
//                                val icon =
//                                    if (ispasswordVisible) Icons.Default.Lock else Icons.Default.Search
//                                Icon(
//                                    imageVector = icon, contentDescription = ""
//                                )
//                            }
//                        }
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    // error msg
//
//                    if (myErrrormessages != null) {
//                        Text(
//                            text = myErrrormessages!!, color = Color.Red,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(8.dp)
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Button(
//                            onClick = {
//                                if (isSignin) {
//                                    signIn(auth, email, password,
//                                        onSignedIn = { signedInUser ->
//                                            onSignedIn(signedInUser)
//                                        },
//                                        onSignInError = { errorMessage ->
//                                            // Show toast message on sign-in error
//                                            myErrrormessages = errorMessage
//                                        }
//                                    )
//                                } else {
//
//                                    SignUp(
//                                        auth = auth,
//                                        email,
//                                        password,
//                                        firstName,
//                                        lastName,
//                                        onSignIn = { SignedInUser ->
//                                            onSignedIn(SignedInUser)
//
//
//                                        })
//                                }
//
//                            },
//                            modifier = Modifier.fillMaxWidth().height(60.dp).padding(8.dp)
//                        ) {
//                            Text(
//                                text = if (isSignin) "Signin" else "SignUp",
//                                fontSize = 18.sp
//
//                            )
//
//
//                        }
//
//                    }
//                    Box(modifier = Modifier.fillMaxWidth().height(8.dp).padding(8.dp)) {
//                        ClickableText(text = AnnotatedString(buildAnnotatedString {
//                            withStyle(style = SpanStyle(color = Color.Blue))
//                            {
//                                append(if (isSignin) "Don't have an account Sign up " else "Alread have accout ? Sing In")
//                            }
//                        }.toString()), onClick = {
//                            myErrrormessages = null
//                            email = ""
//                            password = ""
//                            isSignin = !isSignin
//                        },
//                            modifier = Modifier.align(alignment = Alignment.Center)
//                        )
//
//                    }
//
//
//                }
//            }
//        }
//
//
//    }
//
//
//    fun SignUp(
//        auth: FirebaseAuth,
//        email: String,
//        password: String,
//        firstName: String,
//        lastName: String,
//        onSignIn: (FirebaseUser) -> Unit
//    ) {
//
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener {
//                if (it.isSuccessful) {
//                    val user = auth.currentUser
//                    //create user profile in Firestore
//                    val userProfile = hashSetOf(
//                        "firstName" to firstName,
//                        "lastName" to lastName,
//                        "email" to email
//                    )
//                    val firestore = FirebaseFirestore.getInstance()
//                        .document(user!!.uid)
//                        .set(userProfile)
//                        .addOnSuccessListener {
//                            onSignIn(user)
//                        }
//                        .addOnFailureListener {
//
//                        }
//                } else {
//
//                }
//            }
//    }
//
//
//    private fun signIn(
//        auth: FirebaseAuth,
//        email: String,
//        password: String,
//        onSignedIn: (FirebaseUser) -> Unit,
//        onSignInError: (String) -> Unit // Callback for sign-in error
//    ) {
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    val user = auth.currentUser
//                    onSignedIn(user!!)
//                } else {
//                    // Handle sign-in failure
//                    onSignInError("Invalid email or password")
//                }
//            }
//    }
//
//    private fun onSignInERROR(errormessage: String) {
//        print("Sign in Error $errormessage")
//
//    }
    }
}
data class User1(
    val firstName: String?,
    val lastName: String?,
    val email: String
)


