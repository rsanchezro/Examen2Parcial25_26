package com.example.examen2parcial25_26.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.examen2parcial25_26.modelo.Contacto
import com.example.examen2parcial25_26.ui.pantallas.pantallaContactosDispositivos
import com.example.examen2parcial25_26.ui.pantallas.pantallaMisContactos
import com.example.examen2parcial25_26.viewmodel.ContactosViewModel


@Composable
fun miNavHost(modificador: Modifier= Modifier, control_navegacion: NavHostController,contactos: MutableList<Contacto> , onclick_cambiarfoto:(c:Contacto)->Unit)
{

    NavHost(navController = control_navegacion,
        startDestination = Ruta.ContactosApp, modifier = modificador)
    {
        composable<Ruta.ContactosApp>{
            pantallaMisContactos(contactos,onclick_cambiarfoto=onclick_cambiarfoto)
        }

        composable<Ruta.ContactosSistema> {

        }
    }


}