package br.jus.tse.eleitoral.etitul.ui.theme.displays

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


data class CandyElement(
    val picture: Int,
    val isAnimate: Boolean = false,
    val info: String = ""
)


@Composable
fun Display3(navigation: NavHostController){

    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val listOfDrawables = listOf<CandyElement>(
        CandyElement(picture = R.drawable.sugar),
        CandyElement(picture = R.drawable.lollipop),
        CandyElement(picture = R.drawable.jellybean),
        CandyElement(picture = R.drawable.gumdrop),
        CandyElement(picture = R.drawable.sugar),
        CandyElement(picture = R.drawable.lollipop),
        CandyElement(picture = R.drawable.jellybean),
        CandyElement(picture = R.drawable.gumdrop),
        CandyElement(picture = R.drawable.sugar),
        CandyElement(picture = R.drawable.lollipop),
        CandyElement(picture = R.drawable.jellybean),
        CandyElement(picture = R.drawable.gumdrop),
        CandyElement(picture = R.drawable.sugar),
        CandyElement(picture = R.drawable.lollipop),
        CandyElement(picture = R.drawable.jellybean),
        CandyElement(picture = R.drawable.gumdrop)
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
        mutableIntStateOf(20)
    }

    if (time.intValue < 10){
        fontColor.value = CandyRed
    }

    val isLocked = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit){
        repeat(20){
            delay(1000)
            time.intValue -= 1
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
            text = "Time remains: ${time.intValue}",
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


        suspend fun animateAndAddNewElements(positionsToAnimateSet: MutableSet<Int>){

            val tmpImageListWithRandomImages = listOfDrawables.shuffled()
            val tmpList = listOfElementsForTheGame.value.toMutableList()
            for(i in positionsToAnimateSet){
                tmpList[i] = tmpList[i].copy(isAnimate = true)
            }
            listOfElementsForTheGame.value = tmpList
            //animate
            delay(600)

            for (i in positionsToAnimateSet){
                tmpList[i] = tmpList[i].copy(picture = tmpImageListWithRandomImages[i].picture, isAnimate = false)
            }
            listOfElementsForTheGame.value = tmpList



        }

        suspend fun checkWinner(){

            val positionsToAnimateSet: MutableSet<Int> = mutableSetOf()

            val setRow1 = mutableSetOf<Int>()
            for (i in 0..3){
                setRow1.add(listOfElementsForTheGame.value[i].picture)
            }

            if (setRow1.size == 1){
                scores.intValue += 1
                positionsToAnimateSet.add(0)
                positionsToAnimateSet.add(1)
                positionsToAnimateSet.add(2)
                positionsToAnimateSet.add(3)
            }

            val setRow2 = mutableSetOf<Int>()
            for (i in 4..7){
                setRow2.add(listOfElementsForTheGame.value[i].picture)
            }

            if (setRow2.size == 1){
                scores.intValue += 1
                positionsToAnimateSet.add(4)
                positionsToAnimateSet.add(5)
                positionsToAnimateSet.add(6)
                positionsToAnimateSet.add(7)
            }

            val setRow3 = mutableSetOf<Int>()
            for (i in 8..11){
                setRow3.add(listOfElementsForTheGame.value[i].picture)
            }

            if (setRow3.size == 1){
                scores.intValue += 1
                positionsToAnimateSet.add(8)
                positionsToAnimateSet.add(9)
                positionsToAnimateSet.add(10)
                positionsToAnimateSet.add(11)
            }

            val setRow4 = mutableSetOf<Int>()
            for (i in 12..15){
                setRow4.add(listOfElementsForTheGame.value[i].picture)
            }

            if (setRow4.size == 1){
                scores.intValue += 1
                positionsToAnimateSet.add(12)
                positionsToAnimateSet.add(13)
                positionsToAnimateSet.add(14)
                positionsToAnimateSet.add(15)
            }


            val setColumn1 = mutableSetOf<Int>()
            setColumn1.add(listOfElementsForTheGame.value[0].picture)
            setColumn1.add(listOfElementsForTheGame.value[4].picture)
            setColumn1.add(listOfElementsForTheGame.value[8].picture)
            setColumn1.add(listOfElementsForTheGame.value[12].picture)

            if (setColumn1.size == 1){
                scores.intValue += 2
                positionsToAnimateSet.add(0)
                positionsToAnimateSet.add(4)
                positionsToAnimateSet.add(8)
                positionsToAnimateSet.add(12)
            }


            val setColumn2 = mutableSetOf<Int>()
            setColumn2.add(listOfElementsForTheGame.value[1].picture)
            setColumn2.add(listOfElementsForTheGame.value[5].picture)
            setColumn2.add(listOfElementsForTheGame.value[9].picture)
            setColumn2.add(listOfElementsForTheGame.value[13].picture)


            if (setColumn2.size == 1){
                scores.intValue += 2
                positionsToAnimateSet.add(1)
                positionsToAnimateSet.add(5)
                positionsToAnimateSet.add(9)
                positionsToAnimateSet.add(13)
            }

            val setColumn3 = mutableSetOf<Int>()
            setColumn3.add(listOfElementsForTheGame.value[2].picture)
            setColumn3.add(listOfElementsForTheGame.value[6].picture)
            setColumn3.add(listOfElementsForTheGame.value[10].picture)
            setColumn3.add(listOfElementsForTheGame.value[14].picture)


            if (setColumn3.size == 1){
                scores.intValue += 2
                positionsToAnimateSet.add(2)
                positionsToAnimateSet.add(6)
                positionsToAnimateSet.add(10)
                positionsToAnimateSet.add(14)
            }

            val setColumn4 = mutableSetOf<Int>()
            setColumn4.add(listOfElementsForTheGame.value[3].picture)
            setColumn4.add(listOfElementsForTheGame.value[7].picture)
            setColumn4.add(listOfElementsForTheGame.value[11].picture)
            setColumn4.add(listOfElementsForTheGame.value[15].picture)

            if (setColumn4.size == 1){
                scores.intValue += 2
                positionsToAnimateSet.add(3)
                positionsToAnimateSet.add(7)
                positionsToAnimateSet.add(11)
                positionsToAnimateSet.add(15)
            }

            animateAndAddNewElements(positionsToAnimateSet)

        }

        fun swapElementsInList(touchedElement: Int, elementToSwap: Int){
            if (!isLocked.value){
                isLocked.value = true
                val scope = MainScope()
                scope.launch {
                    val newMutableList = listOfElementsForTheGame.value.toMutableList()
                    val touched = listOfElementsForTheGame.value[touchedElement]
                    val toSwap = listOfElementsForTheGame.value[elementToSwap]
                    newMutableList[touchedElement] = toSwap
                    newMutableList[elementToSwap] = touched
                    listOfElementsForTheGame.value = newMutableList.toList()
                    delay(100)
                    checkWinner()
                    isLocked.value = false
                }
            }
        }



        fun swapRight(position: Int){
            if (position == 3 || position == 7 || position == 11 || position == 15){
                //do nothing
            } else {
                val swap = position + 1
                swapElementsInList(position, swap)
            }
        }

        fun swapLeft(position: Int){
            if (position == 0 || position == 4 || position == 8 || position == 12){
                //do nothing
            } else {
                val swap = position -1
                swapElementsInList(position, swap)
            }
        }

        fun swapUp(position: Int){
            if (position in 4..15){
                val swap = position - 4
                swapElementsInList(position, swap)
            }
        }

        fun swapDown(position: Int){
            if (position in 0..11){
                val swap = position + 4
                swapElementsInList(position, swap)
            }
        }

        LazyVerticalGrid(
            modifier = Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            columns = GridCells.Fixed(4),
            content = {
                items(16){ position ->

                    val transitionIn = updateTransition(listOfElementsForTheGame.value[position].isAnimate, label = "")
                    val alphaIn by transitionIn.animateFloat(
                        transitionSpec = { tween(durationMillis = 800) },
                        label = ""
                    ) { isAnimate ->
                        if (isAnimate) 0f else 1f
                    }

                    Image(
                        painter = painterResource(id = listOfElementsForTheGame.value[position].picture),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .alpha(alphaIn)
                            .pointerInput(Unit) {
                                detectDragGestures { change, dragAmount ->
                                    change.consume()
                                    if (dragAmount.x.toDp() > 7.dp) {
                                        swapRight(position)
                                    } else if (dragAmount.x.toDp() < (-7).dp) {
                                        swapLeft(position)
                                    } else if (dragAmount.y.toDp() > 7.dp) {
                                        swapDown(position)
                                    } else if (dragAmount.y.toDp() < (-7).dp) {
                                        swapUp(position)
                                    }
                                }
                            }
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

        Box(modifier = Modifier
            .fillMaxSize()
        ){
            Image(
                painter = painterResource(id = R.drawable.bonbon),
                contentDescription = "Game Over back",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            Text(
                text = "Game Over \n \n  scores is ${scores.value}",
                fontFamily = font,
                color = CandyWhite,
                fontSize = 48.sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )

            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Close button on game over screen",
                tint = CandyWhite,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(32.dp)
                    .clickable { navigation.navigate(Displays.Display2Route.endPoint) }
            )
        }
    }
}