package com.example.examen2parcial25_26.utilidades

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

/*Funcion para generar una URI, se deberá invocar antes de lanzar
* la intent de capturar imagen */

fun crearArchivoImagen(context:Context,nombre:String):File{
    val nombreArchivo="cont_${nombre}_${System.currentTimeMillis()}.jpg"
    return File(context.filesDir,nombreArchivo)
}
fun crearUriImagen(context: Context,f:File): Uri {


    return  FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        f
    )
}
d