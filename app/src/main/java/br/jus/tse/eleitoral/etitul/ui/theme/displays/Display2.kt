package br.jus.tse.eleitoral.etitul.ui.theme.displays

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
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
import br.jus.tse.eleitoral.etitul.ui.theme.CandyWhite
import br.jus.tse.eleitoral.etitul.ui.theme.Displays

@Composable
fun Display2(navigation: NavHostController){

    Image(
        painter = painterResource(id = R.drawable.bg),
        contentDescription = "bg",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Box(modifier = Modifier.fillMaxSize()){
        Decor()
        Buttons(navigation)
    }
}

@Composable
fun Buttons(navigation: NavHostController) {

    val font = FontFamily(Font(R.font.country_font))
    val activity = LocalContext.current as Activity

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .padding(top = 64.dp)
            .align(Alignment.TopCenter)
        ){
            Box {
                Image(
                    painter = painterResource(id = R.drawable.cotton),
                    contentDescription = "button",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navigation.navigate(Displays.Display3Route.endPoint)
                        }
                )

                Text(
                    text = "Play",
                    color = CandyWhite,
                    fontFamily = font,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box {
                Image(
                    painter = painterResource(id = R.drawable.cotton),
                    contentDescription = "button",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            activity.moveTaskToBack(true)
                            activity.finish()
                        }
                )

                Text(
                    text = "Exit",
                    color = CandyWhite,
                    fontFamily = font,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }


        }
    }
}

@Composable
fun BoxScope.Decor(){
    Image(
        painter = painterResource(id = R.drawable.sprinkles),
        contentDescription = "dec1",
        modifier = Modifier
            .align(Alignment.BottomStart)
    )

    Image(
        painter = painterResource(id = R.drawable.licorice),
        contentDescription = "dec2",
        modifier = Modifier
            .align(Alignment.BottomEnd)
    )

    Image(
        painter = painterResource(id = R.drawable.caramel),
        contentDescription = "cristals",
        modifier = Modifier
            .align(Alignment.CenterStart)
            .scale(2f)
            .padding(start = 64.dp)
            .rotate(35f)
            .offset(y = 32.dp)
    )

    Image(
        painter = painterResource(id = R.drawable.caramel),
        contentDescription = "cristals",
        modifier = Modifier
            .align(Alignment.CenterEnd)
            .scale(2f)
            .padding(end = 32.dp)
            .rotate(-40f)
    )


}

@Composable
@Preview
fun Display2Prev(){
    val nav = rememberNavController()
    Display2(navigation = nav)
}