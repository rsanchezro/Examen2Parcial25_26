package com.example.examen2parcial25_26.navegacion

import kotlinx.serialization.Serializable

sealed class Ruta{
    @Serializable
    object ContactosApp: Ruta()
    @Serializable
    object ContactosSistema:Ruta()
}