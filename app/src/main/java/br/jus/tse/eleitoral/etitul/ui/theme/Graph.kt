package br.jus.tse.eleitoral.etitul.ui.theme

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.jus.tse.eleitoral.etitul.ui.theme.displays.Display1
import br.jus.tse.eleitoral.etitul.ui.theme.displays.Display2
import br.jus.tse.eleitoral.etitul.ui.theme.displays.Display3


@Composable
fun Graph(){

    val navigation = rememberNavController()

    NavHost(navController = navigation, startDestination = Displays.Display1Route.endPoint){

        composable(route = Displays.Display1Route.endPoint){
            Display1(navigation = navigation)
        }

        composable(route = Displays.Display2Route.endPoint){
            Display2(navigation = navigation)
        }

        composable(route = Displays.Display3Route.endPoint){
            Display3(navigation = navigation)
        }
    }
}