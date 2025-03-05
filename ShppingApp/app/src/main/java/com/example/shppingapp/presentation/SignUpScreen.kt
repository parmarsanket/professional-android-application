package com.example.shppingapp.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shppingapp.R
import com.example.shppingapp.ScreenA
import com.example.shppingapp.ScreenB
import com.example.shppingapp.presentation.compodents.CustomButton
import com.example.shppingapp.presentation.compodents.CustomTextField
import com.google.firebase.auth.FirebaseAuth


@Composable
fun SignUpScreen(modifier: Modifier = Modifier,navController: NavController) {
    val context = LocalContext.current
    var firstName by remember { mutableStateOf("") }
    var LastName by remember { mutableStateOf("") }
    var Email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var compassword by remember { mutableStateOf("") }
    var phonenum by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "SingUp",
            fontSize = 24.sp,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(alignment = Alignment.Start)
        )

        CustomTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = "First Name",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 16.dp),
            leadingIcon = Icons.Default.Person,
        )

        CustomTextField(
            value = LastName,
            onValueChange = { LastName = it },
            label = "Last Name",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 16.dp),
            leadingIcon = Icons.Default.Person,
        )

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
        CustomTextField(
            value = compassword,
            onValueChange = { compassword = it },
            label = "Confirm Password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 16.dp),
            leadingIcon = Icons.Default.Lock,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        CustomTextField(
            value = phonenum,
            onValueChange = { phonenum = it },
            label = "Phone Number",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 16.dp),
            leadingIcon = Icons.Default.Phone,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        CustomButton(
            name = "SingUp",
//            enable = (firstName.isNotEmpty() && LastName.isNotEmpty() && Email.isNotEmpty() && password.isNotEmpty()
//                    && compassword.isNotEmpty() && phonenum.isNotEmpty()),
            onClick = {
                if (firstName.isNotEmpty() && LastName.isNotEmpty() && Email.isNotEmpty() && password.isNotEmpty()
                    && compassword.isNotEmpty() && phonenum.isNotEmpty()
                ) {
                    if (password == compassword) {
                        val auth = FirebaseAuth.getInstance()
                        auth.createUserWithEmailAndPassword(Email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        context,
                                        "Successfully regester  ",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }else {
                                    val message = task.exception?.message ?: "Signup failed"
                                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                }
                            }



                    } else {
                        Toast.makeText(
                            context,
                            "password and confirm password not watch",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(context, "Please fill the Field", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Already Have an account?")
            TextButton(onClick = {navController.navigate(ScreenA)}) {
                Text("Login", color = colorResource(R.color.orenge) )
            }
        }
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
            onClick = {}
        ) {
            Image(painter = painterResource(R.drawable.google), contentDescription = null , modifier = Modifier.size(24.dp))
            Spacer(modifier=Modifier.padding(vertical = 16.dp, horizontal = 8.dp))
            Text("Log in with Google", color = MaterialTheme.colorScheme.onBackground)
        }
        

    }
}
fun registerUser(email: String, password: String) {

}