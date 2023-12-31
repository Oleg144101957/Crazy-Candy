package br.jus.tse.eleitoral.etitul.ui.theme.displays

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
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
import br.jus.tse.eleitoral.etitul.ui.theme.Jcnjkdkfkdjhgjkdlf
import kotlinx.coroutines.delay


@Composable
fun Display1(navigation: NavHostController){

    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val font = FontFamily(Font(R.font.country_font))
    val rotationState = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(Unit){
        rotationState.animateTo(
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = 1700, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    LaunchedEffect(Unit){
        delay(2800)
        navigation.navigate(Jcnjkdkfkdjhgjkdlf.Display2Route.nvdskjlnvdfksl)
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

    BackHandler(enabled = true) {
        //do nothing
    }
}

@Composable
@Preview
fun Display1Prev(){
    val nav = rememberNavController()
    Display1(navigation = nav)

}

