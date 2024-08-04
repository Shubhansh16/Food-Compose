package com.example.foodcomp.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodcomp.Navigation
import com.example.foodcomp.ui.components.TabLayout

@Composable
fun AuthScreen(navController: NavController){
   val context = LocalContext.current
   val sharedPreferences = context.getSharedPreferences("main", Context.MODE_PRIVATE)

   Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
       val selectedTab = remember {
          mutableIntStateOf(0)
       }
      TabLayout(
         selectedIndex = selectedTab.intValue,
         items = listOf(
            "Sign In" to {},
            "Sign Up" to {}
         ),
         onTabClick = {
            selectedTab.intValue = it
         }
      )
   }
}

@Composable
@Preview(showBackground = true)
fun AuthScreenPreview(){
   val navController = rememberNavController()
   AuthScreen(navController = navController)
}
