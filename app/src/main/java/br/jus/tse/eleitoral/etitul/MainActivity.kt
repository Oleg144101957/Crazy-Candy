package br.jus.tse.eleitoral.etitul

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.jus.tse.eleitoral.etitul.ui.theme.CrazyCandyTheme
import br.jus.tse.eleitoral.etitul.ui.theme.Graph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm by viewModels<CrazyVM>()

        setContent {
            CrazyCandyTheme {
                // A surface container using the 'background' color from the theme
                Graph(vm)
            }
        }
    }
}
