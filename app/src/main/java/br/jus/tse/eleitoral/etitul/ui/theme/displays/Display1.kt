package br.jus.tse.eleitoral.etitul.ui.theme.displays

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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


@Composable
fun Display1(navigation: NavHostController){

    val font = FontFamily(Font(R.font.country_font))

    val rotationState = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(Unit){
        rotationState.animateTo(
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = 2000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Image(
        painter = painterResource(id = R.drawable.bg),
        contentDescription = "bg",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )


    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.sprinkles),
            contentDescription = "decor1",
            modifier = Modifier
                .align(Alignment.BottomStart)
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(60.dp)
                .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
        )

        Image(
            painter = painterResource(id = R.drawable.nougat),
            contentDescription = "star",
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer {
                    rotationZ = rotationState.value
                }
        )

        Text(
            text = "Loading...",
            color = Color.Black,
            fontFamily = font,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 64.dp)
        )
    }
}

@Composable
@Preview
fun Display1Prev(){
    val nav = rememberNavController()
    Display1(navigation = nav)

}

