package br.jus.tse.eleitoral.etitul.ui.theme.displays

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.jus.tse.eleitoral.etitul.R
import br.jus.tse.eleitoral.etitul.ui.theme.CandyRed
import br.jus.tse.eleitoral.etitul.ui.theme.CandyWhite
import br.jus.tse.eleitoral.etitul.ui.theme.Displays
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun Display3(navigation: NavHostController){

    val listOfDrawables = listOf(
        R.drawable.sugar,
        R.drawable.lollipop,
        R.drawable.jellybean,
        R.drawable.gumdrop,
        R.drawable.sugar,
        R.drawable.lollipop,
        R.drawable.jellybean,
        R.drawable.gumdrop,
        R.drawable.sugar,
        R.drawable.lollipop,
        R.drawable.jellybean,
        R.drawable.gumdrop,
        R.drawable.sugar,
        R.drawable.lollipop,
        R.drawable.jellybean,
        R.drawable.gumdrop
    )

    val listOfElementsForTheGame = remember{
        mutableStateOf(listOfDrawables.shuffled())
    }


    val font = FontFamily(Font(R.font.country_font))

    val fontColor = remember {
        mutableStateOf(CandyWhite)
    }

    val scores = remember {
        mutableIntStateOf(0)
    }
    val time = remember {
        mutableIntStateOf(60)
    }


    if (time.value < 10){
        fontColor.value = CandyRed
    }

    LaunchedEffect(Unit){
        repeat(60){
            delay(1000)
            time.value -= 1
        }
    }

    Image(
        painter = painterResource(id = R.drawable.bg),
        contentDescription = "bg",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Box(modifier = Modifier.fillMaxSize()){

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "back button",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .size(48.dp)
                .clickable {
                    navigation.navigate(Displays.Display2Route.endPoint)
                }
        )

        Text(
            text = "Time remains: ${time.value}",
            color = fontColor.value,
            fontFamily = font,
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.bonbon),
            contentDescription = "back play",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            contentScale = ContentScale.FillWidth
        )

        LazyVerticalGrid(
            modifier = Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            columns = GridCells.Fixed(4),
            content = {
                items(16){ position ->

                    Log.d("123123", "The number is $position")

                    Image(
                        painter = painterResource(id = listOfElementsForTheGame.value[position]),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    )
                }
            }
        )

        Box(modifier = Modifier.align(Alignment.BottomCenter)){
            Image(
                painter = painterResource(id = R.drawable.cotton),
                contentDescription = "scores back",
                modifier = Modifier
                    .align(Alignment.Center)
            )

            Text(
                text = "Scores: ${scores.value}",
                color = CandyWhite,
                fontFamily = font,
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    if (time.value <= 0){

Box(modifier = Modifier.fillMaxSize()){
    Image(
        painter = painterResource(id = R.drawable.bonbon),
        contentDescription = "Game Over back",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Text(
        text = "Game Over \n scores is ${scores.value}",
        fontFamily = font,
        color = CandyWhite,
        fontSize = 48.sp,
        modifier = Modifier.align(Alignment.Center)
    ) }
    }
}


@Composable
@Preview
fun GamePrev(){
    val nav = rememberNavController()
    Display3(navigation = nav)
}