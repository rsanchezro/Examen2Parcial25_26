package com.example.examen2parcial25_26

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.examen2parcial25_26.navegacion.miNavHost
import com.example.examen2parcial25_26.ui.componentes.miTopAppBar
import com.example.examen2parcial25_26.ui.theme.Examen2Parcial25_26Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Examen2Parcial25_26Theme {

                }
            }
        }
    }

@Composable
fun pantallaPrincipal()
{
    //Definimos el controlador de navegación
    val controlador_navegacion= rememberNavController()
    Scaffold (modifier = Modifier.fillMaxSize().statusBarsPadding(), topBar = { miTopAppBar() }, bottomBar = {}){
        miNavHost(Modifier.padding(it),controlador_navegacion)

    }
}
