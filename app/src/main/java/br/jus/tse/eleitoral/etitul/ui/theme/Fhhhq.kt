package br.jus.tse.eleitoral.etitul.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.jus.tse.eleitoral.etitul.CrazyVM
import br.jus.tse.eleitoral.etitul.ui.theme.displays.Display1
import br.jus.tse.eleitoral.etitul.ui.theme.displays.Display2
import br.jus.tse.eleitoral.etitul.ui.theme.displays.Display3


@Composable
fun Graph(vm: CrazyVM){

    val navigation = rememberNavController()

    NavHost(navController = navigation, startDestination = Jcnjkdkfkdjhgjkdlf.Display1Route.nvdskjlnvdfksl){

        composable(route = Jcnjkdkfkdjhgjkdlf.Display1Route.nvdskjlnvdfksl){
            Display1(navigation = navigation)
        }

        composable(route = Jcnjkdkfkdjhgjkdlf.Display2Route.nvdskjlnvdfksl){
            Display2(navigation = navigation)
        }

        composable(route = Jcnjkdkfkdjhgjkdlf.Display3Route.nvdskjlnvdfksl){
            Display3(navigation = navigation, vm = vm)
        }
    }
}