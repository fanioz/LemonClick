package com.fanioz.lemonclick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fanioz.lemonclick.ui.theme.LemonClickTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonClickTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonClickState(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LemonClickState(modifier: Modifier = Modifier) {
    var result by remember {
        mutableIntStateOf(1)
    }
    var sequeeze by remember {
        mutableIntStateOf(5)
    }
    LemonClickStateless(
        step = result, modifier,
    ) {
        if (result == 2 && sequeeze > 0) {
            sequeeze -= 1
        } else {
            result = if (result > 3) 1 else result + 1
            sequeeze = 5
        }
    }
}

@Composable
fun LemonClickStateless(step: Int, modifier: Modifier = Modifier, onClick: () -> Unit
) {

    val imageResource = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val txtButton = when (step) {
        1 -> R.string.select
        2 -> R.string.squeeze
        3 -> R.string.drink
        else -> R.string.restart
    }

    val txtDescription = when (step) {
        1 -> R.string.treeImg
        2 -> R.string.lemonimg
        3 -> R.string.glassOfLemonade
        else -> R.string.emtyglass
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(id = txtDescription),
            modifier = Modifier.clickable{
                onClick()
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        //make text modifier with background color blue


        Text(
            stringResource(id = txtButton)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LemonClickTheme {
        LemonClickStateless(step = 1, onClick = {})
    }
}