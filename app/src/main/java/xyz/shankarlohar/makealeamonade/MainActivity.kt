package xyz.shankarlohar.makealeamonade

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.shankarlohar.makealeamonade.ui.theme.MakeALeamonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MakeALeamonadeTheme {
                 LemonadeScreen()
            }
        }
    }
}

@Composable
fun LemonadeScreen() {
    LemonadeSteps()
}

@Preview(showBackground = true)
@Composable
fun LemonadeSteps() {
    var step by remember {
        mutableStateOf(1)
    }
    var squeeze by remember {
        mutableStateOf(0)
    }
    val (image,text) = when(step){
        1 -> arrayListOf(R.drawable.lemon_tree,R.string.lemon_tree_description)
        2 -> arrayListOf(R.drawable.lemon_squeeze,R.string.lemon_squeeze_description)
        3 -> arrayListOf(R.drawable.lemon_drink,R.string.lemon_drink_description)
        else -> arrayListOf(R.drawable.lemon_restart,R.string.lemon_restart_description)
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = stringResource(text))
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(image),
            contentDescription = stringResource(text),
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    when (step) {
                        1 -> {
                            squeeze = (2..4).random()
                            step++
                        }
                        2 -> if (squeeze > 0) squeeze-- else step++
                        3 -> step++
                        4 -> step = 1
                    }
                }
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}