package com.example.shppingapp.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shppingapp.R
import com.example.shppingapp.ScreenB
import com.example.shppingapp.ScreencC
import com.example.shppingapp.presentation.compodents.CustomButton
import com.example.shppingapp.presentation.compodents.CustomTextField
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.qualifiers.ApplicationContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(modifier: Modifier = Modifier,navController: NavController) {


    var Email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val context = LocalContext.current


    Scaffold (
        topBar ={
                loginTopbar( scrollBehavior = scrollBehavior)
        } ,

        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

    ){
        LazyColumn (modifier = modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            item {
                CustomTextField(
                    value = Email,
                    onValueChange = { Email = it },
                    label = "Email",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 16.dp),
                    leadingIcon = Icons.Default.Email,
                )

                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 16.dp),
                    leadingIcon = Icons.Default.Lock,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
            item {
                CustomButton(
                    name = "SingUp",
//            enable = (firstName.isNotEmpty() && LastName.isNotEmpty() && Email.isNotEmpty() && password.isNotEmpty()
//                    && compassword.isNotEmpty() && phonenum.isNotEmpty()),
                    onClick = {



                            val auth = FirebaseAuth.getInstance()
                        if (Email.isNotEmpty() && password.isNotEmpty()) {
                            auth.signInWithEmailAndPassword(Email, password)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        val user = auth.currentUser // Get the logged-in user
                                        val userId = user?.uid
                                        Toast.makeText(
                                            context,
                                            "login SuccessFully",
                                            Toast.LENGTH_SHORT
                                        ).show()


                                        navController.navigate(ScreencC(auth.currentUser?.uid.toString()))
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "invalid username and password",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                         }
                        else{
                            Toast.makeText(
                                context,
                                "username or password is empty",
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                        },
                    modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp)
                )

            }
            item {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Don't Have an account?")
                    TextButton(onClick = {
                        navController.navigate(ScreenB)
                    }) {
                        Text("SignUp", color = colorResource(R.color.orenge) )
                    }
                }
            }
            item {
                Row (modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    HorizontalDivider(modifier = Modifier.weight(1f))
                    Text("OR", modifier = Modifier.padding(horizontal = 8.dp))
                    HorizontalDivider(modifier = Modifier.weight(1f))
                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                    }
                ) {
                    Image(painter = painterResource(R.drawable.google), contentDescription = null , modifier = Modifier.size(24.dp))
                    Spacer(modifier=Modifier.padding(vertical = 16.dp, horizontal = 8.dp))
                    Text("Log in with Google", color = MaterialTheme.colorScheme.onBackground)
                }

            }

        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginTopbar(scrollBehavior:TopAppBarScrollBehavior)
{
    LargeTopAppBar(
        windowInsets = WindowInsets(0),
        title = { Text("Login") }
        , scrollBehavior =  scrollBehavior
    )
}