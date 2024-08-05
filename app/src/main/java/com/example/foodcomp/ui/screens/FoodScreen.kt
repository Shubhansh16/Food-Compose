package com.example.foodcomp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodcomp.ui.theme.poppinsFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(navController: NavController){
   Scaffold(
       modifier = Modifier.padding(top = 20.dp),
       topBar = {
           CenterAlignedTopAppBar(
               modifier = Modifier.shadow(8.dp),
               navigationIcon = {
                   Row {
                      Spacer(modifier = Modifier.width(8.dp))
                       Icon(imageVector = Icons.Rounded.ArrowBack,
                           contentDescription =null,
                           modifier = Modifier
                               .size(30.dp)
                               .clickable {
                                   navController.popBackStack()
                               })
                   }
               },
               title = {
                   Text(text = "Food", fontFamily = poppinsFont, fontWeight = FontWeight.Bold)
               }
           )
       }
   ) {paddings->
     Column(
         modifier = Modifier
             .fillMaxSize()
             .padding(paddings)
             .verticalScroll(rememberScrollState())
     ) {
           Image(painter = painterResource(id = foods.random().image),
               modifier = Modifier
                   .fillMaxWidth()
                   .heightIn(max = 200.dp),
               contentDescription = null,
               contentScale = ContentScale.Crop
           )
           Spacer(modifier = Modifier.height(16.dp))
         Column(
             modifier = Modifier.padding(horizontal = 16.dp)
         ) {
             Text(
                 text = foods.random().name,
                 fontSize = 18.sp,
                 fontFamily = poppinsFont,
                 fontWeight = FontWeight.Bold
             )
             Spacer(modifier = Modifier.height(16.dp))
             Text(text = "₹187", fontSize = 17.sp, fontFamily = poppinsFont)
             Spacer(modifier = Modifier.height(16.dp))
             Text(
                 text = "Description",
                 fontSize = 14.sp,
                 fontFamily = poppinsFont,
                 fontWeight = FontWeight.Bold
             )
             Spacer(modifier = Modifier.height(2.dp))
             Text(
                 text = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source",
                 fontFamily = poppinsFont,
                 fontSize = 13.sp,
                 textAlign = TextAlign.Justify
             )
         }
             Spacer(modifier = Modifier.height(16.dp))
             Divider(thickness = 2.dp)
             Spacer(modifier = Modifier.height(16.dp))

             Text(text = "Recommended Foods:", modifier = Modifier.padding(horizontal = 8.dp),
                 fontWeight = FontWeight.Bold, fontSize = 17.sp, fontFamily = poppinsFont
                 )
             Spacer(modifier = Modifier.height(16.dp))
             LazyRow(
                 horizontalArrangement = Arrangement.spacedBy(16.dp)
             ) {
                items(foods){food->
                    RecommendedFood(food = food, onTap = {food->
                   //
                    } )
                }
             }
         Spacer(modifier = Modifier.height(16.dp))
         Divider(thickness = 2.dp)
         Spacer(modifier = Modifier.height(16.dp))
         Column(
             modifier = Modifier.padding(horizontal = 8.dp)
         ) {
            Text(text = "Rating and Reviews", fontSize = 18.sp, fontFamily = poppinsFont, fontWeight = FontWeight.Bold)
             Spacer(modifier = Modifier.height(8.dp))
             Text(
                 text = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source",
                 fontFamily = poppinsFont,
                 fontSize = 13.sp,
                 textAlign = TextAlign.Justify
             )
         }
         Spacer(modifier = Modifier.height(8.dp))
     }
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendedFood(food: Food, onTap:(Food)->Unit){
   Card(
       elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
       colors = CardDefaults.cardColors(
           containerColor = Color(0xfff1f1f1)
       ),
       onClick =  {
           onTap(food)
       }
   ) {
       Column {
           Image(painter = painterResource(id = food.image),
               modifier = Modifier.sizeIn(
                   maxWidth = 120.dp,
                   maxHeight = 70.dp
               ),
               contentDescription =food.name,
               contentScale = ContentScale.Crop
           )
           Column(
               modifier = Modifier.padding(horizontal = 8.dp)
           ){
               Spacer(modifier = Modifier.height(8.dp))
               Text(text = food.name, color = Color(0xff313131),  fontSize = 13.sp, fontFamily = poppinsFont)
               Spacer(modifier = Modifier.height(4.dp))
               Text(text = "₹${food.price}")
               Spacer(modifier = Modifier.height(4.dp))
           }
       }
   }
}

@Composable
@Preview(showBackground = true)
fun FoodScreenPreview(){
    val navController= rememberNavController()
    FoodScreen(navController = navController)
}