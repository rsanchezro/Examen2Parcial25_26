package com.example.examen2parcial25_26.ui.pantallas

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.examen2parcial25_26.modelo.Contacto
import com.example.examen2parcial25_26.ui.componentes.elemento_contacto

@Composable
fun pantallaMisContactos(miscontactos:MutableList<Contacto>,onclick_cambiarfoto:(Contacto)->Unit)
{
    LazyColumn {
        items(miscontactos){contact->

            elemento_contacto(contact,onclick_cambiarfoto)
        }
    }


}