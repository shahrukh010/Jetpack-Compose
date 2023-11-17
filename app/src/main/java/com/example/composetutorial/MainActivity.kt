package com.example.composetutorial

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    MyApp()
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {

    var moneyCounter = remember {
        mutableStateOf(0);

    }
    androidx.compose.material.Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = Color(0xFF546E7A)
    )
    {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "${moneyCounter.value}", style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold

                )
            )
            Spacer(modifier = Modifier.height(130.dp))
            CreateCircle(moneyCounter = moneyCounter.value) { newValue ->

                moneyCounter.value = newValue;
            }

            if (moneyCounter.value > 25) {
                Text(text = "Lots of money")
            }
        }
    }
}

@Composable
fun CreateCircle(moneyCounter: Int = 0, updateMoneyCounter: (Int) -> Unit) {

//    var moneyCounter by remember {
//        mutableStateOf(0);
//    }
    Card(
        modifier = Modifier
            .padding(3.dp)
            .size(100.dp)
            .clickable {

                updateMoneyCounter(moneyCounter + 1);
//                moneyCounter.value += 1;
                Log.d("TAG", "${moneyCounter}")
            },
        shape = CircleShape,
        elevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.Center) {

            // Text(text = "Tap${moneyCounter}", modifier = Modifier)
            Text(text = "Tap", modifier = Modifier)
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    ComposeTutorialTheme {

        MyApp();
    }
}