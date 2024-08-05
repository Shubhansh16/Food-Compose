package com.example.foodcomp.ui.screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodcomp.Navigation
import com.example.foodcomp.R
import com.example.foodcomp.ui.components.TabLayout
import com.example.foodcomp.ui.theme.poppinsFont

@Composable
fun AuthScreen(navController: NavController){
   val context = LocalContext.current
   val sharedPreferences = context.getSharedPreferences("main", Context.MODE_PRIVATE)

   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(horizontal = 20.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
       val selectedTab = remember {
          mutableIntStateOf(0)
       }
      TabLayout(
         selectedIndex = selectedTab.intValue,
         items = listOf(
            "Sign In" to {
                         SignIn(
                            navController = navController,
                            sharedPreferences = sharedPreferences
                         )
            },
            "Sign Up" to {
               SignUp(navController = navController,
                  sharedPreferences = sharedPreferences)
            }
         ),
         onTabClick = {
            selectedTab.intValue = it
         }
      )
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn(
     navController: NavController,
     sharedPreferences: SharedPreferences
){
     val rememberMeChecked = remember {
        mutableStateOf(false)
     }
     val email = remember {
        mutableStateOf("")
     }
     val password = remember {
        mutableStateOf("")
     }
     val showPassword = remember {
        mutableStateOf(false)
     }
     Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
     ) {
       Spacer(modifier = Modifier.height(22.dp))
        AppTextField(
           value = email.value,
           onValueChange = {
                           email.value= it
           },
           label = "E-mail Address"
        )
        Spacer(modifier = Modifier.height(16.dp))
        AppTextField(
           value = password.value,
           onValueChange = {
              password.value= it
           },
           label = "Password",
           visualTransformation = if (showPassword.value)
           VisualTransformation.None
           else
           PasswordVisualTransformation(),
           trailing = {
              Icon(
                 modifier = Modifier
                    .clickable {
                       showPassword.value = !showPassword.value
                    }
                    .size(20.dp),
                 painter = painterResource(id = if(showPassword.value)
                 R.drawable.hidden  else R.drawable.view), contentDescription = null
              )
           }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
           modifier = Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.End
        ){
          Text(text = "Forgot Password", color = Color.Gray, fontSize = 11.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
           modifier = Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.Start,
           verticalAlignment = Alignment.CenterVertically
        ) {
           CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
              Checkbox(checked = rememberMeChecked.value, onCheckedChange = {
                 rememberMeChecked.value = it
              })
           }
           Text(
              text = "Remember Me",
              fontSize = 14.sp,
              modifier = Modifier.padding(horizontal = 8.dp)
           )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
           modifier = Modifier
              .fillMaxWidth()
              .height(40.dp),
           onClick = {
              sharedPreferences.edit().apply {
                 putBoolean("loggedIn", true)
                 putString("email", email.value)
              }
                 .apply()
              navController.navigate("home") {
                 popUpTo(0)
              }
           }, shape = RoundedCornerShape(10.dp)
        ) {
           Text(text = "Login", fontFamily = poppinsFont)
        }
     }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(
   navController: NavController,
   sharedPreferences: SharedPreferences
){
   val rememberMeChecked = remember {
      mutableStateOf(false)
   }
   val email = remember {
      mutableStateOf("")
   }
   val password = remember {
      mutableStateOf("")
   }
   val passwordRepeat = remember {
      mutableStateOf("")
   }
   val showPassword = remember {
      mutableStateOf(false)
   }
   val showPasswordRepeat = remember {
      mutableStateOf(false)
   }
   Column(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Spacer(modifier = Modifier.height(22.dp))
      AppTextField(
         value = email.value,
         onValueChange = {
            email.value= it
         },
         label = "E-mail Address"
      )
      Spacer(modifier = Modifier.height(16.dp))
      AppTextField(
         value = password.value,
         onValueChange = {
            password.value= it
         },
         label = "Password",
         visualTransformation = if (showPassword.value)
            VisualTransformation.None
         else
            PasswordVisualTransformation(),
         trailing = {
            Icon(
               modifier = Modifier
                  .clickable {
                     showPassword.value = !showPassword.value
                  }
                  .size(20.dp),
               painter = painterResource(id = if(showPassword.value)
                  R.drawable.hidden  else R.drawable.view), contentDescription = null
            )
         }
      )

      Spacer(modifier = Modifier.height(16.dp))
      AppTextField(
         value = passwordRepeat.value,
         onValueChange = {
            passwordRepeat.value= it
         },
         label = "Re-Enter Password",
         visualTransformation = if (showPasswordRepeat.value)
            VisualTransformation.None
         else
            PasswordVisualTransformation(),
         trailing = {
            Icon(
               modifier = Modifier
                  .clickable {
                     showPasswordRepeat.value = !showPasswordRepeat.value
                  }
                  .size(20.dp),
               painter = painterResource(id = if(showPasswordRepeat.value)
                  R.drawable.hidden  else R.drawable.view), contentDescription = null
            )
         }
      )
      Spacer(modifier = Modifier.height(32.dp))
      Button(
         modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
         onClick = {
            sharedPreferences.edit().apply {
               putBoolean("loggedIn", true)
               putString("email", email.value)
            }
               .apply()
            navController.navigate("home") {
               popUpTo(0)
            }
         }, shape = RoundedCornerShape(10.dp)
      ) {
         Text(text = "Register", fontFamily = poppinsFont)
      }
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
   value:String,
   onValueChange:(String)-> Unit,
   label:String,
   visualTransformation: VisualTransformation = VisualTransformation.None,
   trailing:(@Composable ()->Unit)? = null
){
   Column {
      Text(text = label)
      Spacer(modifier = Modifier.height(8.dp))
      Box(
         modifier = Modifier
            .shadow(3.dp, RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
      ) {
          TextField(
             modifier = Modifier.fillMaxWidth(),
             value = value,
             onValueChange = onValueChange,
             visualTransformation = visualTransformation,
             singleLine = true,
             colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
             ),
             trailingIcon = trailing
          )
      }
   }
}

@Composable
@Preview(showBackground = true)
fun AuthScreenPreview(){
   val navController = rememberNavController()
   AuthScreen(navController = navController)
}
