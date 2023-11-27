package com.example.composetutorial

import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.component.InputField
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import com.example.composetutorial.widgets.RoundIconButton
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MyApp {
//                Text(text = "Hello Again");
//            }
//            TopHeader();
            MainContent();
        }
    }
}


@Composable
//@Preview(showBackground = true)
fun TopHeader(totalPerPerson: Double = 0.0) {

    val paddingModifier = Modifier.padding(10.dp);
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
        color = Color(0XFFE9D7F7)

    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val total = "%.2f".format(totalPerPerson);
            Text(
                text = "Total Per Person", style = MaterialTheme.typography.h5
            )
            Text(
                text = "$$total", style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
        }

    }

}


@Preview
@Composable
fun MainContent() {

    BillForm() { billAmt ->

        Log.d("Tag", "Main content: ${billAmt.toInt()}")
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(modifier: Modifier = Modifier, onValueChange: (String) -> Unit = {}) {

    var totalBillState = remember {

        mutableStateOf("");
    }
    var split = remember {
        mutableStateOf(1)
    }

    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty();
    }
    val keyboardController = LocalSoftwareKeyboardController.current;

    val sliderPositionState = remember {

        mutableStateOf(0f);
    }



    androidx.compose.material.Surface(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(
            width = 1.dp, color = Color.LightGray
        )
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start
        ) {
            InputField(
                valueState = totalBillState,
                lableId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {

                    if (!validState) return@KeyboardActions;
                    onValueChange(totalBillState.value.trim())
                    keyboardController?.hide()
                })

//            if (validState) {

            Row(
                modifier = Modifier.padding(3.dp),
                horizontalArrangement = Arrangement.Start
            ) {

                Text(
                    text = "Split",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically),
                )

                Spacer(modifier = Modifier.width(120.dp))

                Row(
                    modifier = Modifier.padding(3.dp),
                    horizontalArrangement = Arrangement.End
                ) {

                    RoundIconButton(
                        imageVector = Icons.Default.Remove,
                        onClick = {

                            if (split.value > 1)
                                split.value -= 1;
                        },
                    )
                    Text(
                        text = "${split.value}",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .padding(start = 9.dp, end = 9.dp),
                        fontWeight = FontWeight.Bold
                    )
                    RoundIconButton(
                        imageVector = Icons.Default.Add,
                        onClick = {
                            split.value += 1;
                        })
                }
            }
            //Tip row
            Row(
                modifier = Modifier.padding(
                    horizontal = 3.dp,
                    vertical = 2.dp
                )
            ) {

                Text(
                    text = "Tip",
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(200.dp))
                Text(
                    text = "$33.00",
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "33%")
                Spacer(modifier = Modifier.height(14.dp))

                //add slider
                Slider(value = sliderPositionState.value, onValueChange = { newVal ->

                    sliderPositionState.value = newVal;
                    Log.d("Slider", "BillForm $newVal")
                })
            }
//            }
        }

    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {

    ComposeTutorialTheme {
        Surface(color = MaterialTheme.colors.background) {

            content();
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    ComposeTutorialTheme {

        MyApp {
            Text(text = "Hello Again!");
        }
    }
}

@Preview
@Composable
fun RoundIconButtonPreview() {
    RoundIconButton(
        imageVector = Icons.Default.Add,
        onClick = { /* TODO */ }
    )
}


