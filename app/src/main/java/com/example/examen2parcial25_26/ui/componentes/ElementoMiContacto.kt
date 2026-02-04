package com.example.examen2parcial25_26.ui.componentes

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import com.example.examen2parcial25_26.R
import com.example.examen2parcial25_26.modelo.Contacto
import com.example.examen2parcial25_26.ui.theme.BlueSurface
import com.example.examen2parcial25_26.ui.theme.TextPrimary
import com.example.examen2parcial25_26.ui.theme.TextSecondary
import java.io.File


@Composable

fun elemento_contacto(contacto: Contacto,onclick_cambiarfoto:(original:Contacto,nuevo: Contacto)->Unit) {
    //Obtengo el contexto
    val contexto=LocalContext.current
    //Defino un objeto File vinculado a la Imagen
    var file_imagen by remember { mutableStateOf<File?>(contacto.foto?.let { File(it) }) }
    //Declaro un File para generar el nombre del fichero, no necesita ser state
    var mifile:File?=null
    //Defino el launcher para la intent de tomar foto
    var launcher_foto= rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture(),{
                exito->
            if(exito)
            {
                //La foto se tomo bien
                //cambio el fichero de la foto para que cambie la imagen
                file_imagen=mifile
                //invoco a la función para que el superior cambie la foto del contacto
                onclick_cambiarfoto(contacto,contacto.copy(foto=file_imagen!!.absolutePath))

            }
        }
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = BlueSurface)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {



           ImagenContactoDesdeRuta(file_imagen ){
               //Genero el FILE
               mifile=crearFile(contexto)
               //Genero una URI de una imagen
               var  miuri=crearArchivoImagen(contexto,mifile)
               //Lanzo el launcher para tomar la foto
               launcher_foto.launch(miuri)


           }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
               
            ) {

                Text(
                    text = contacto.nombre,
                    color = TextPrimary,
                    fontSize = 18.sp,          // 🔹 Nombre más grande
                    fontWeight = FontWeight.SemiBold

                )

                Spacer(modifier = Modifier.height(4.dp)) // 🔹 Espacio entre textos

                Text(
                    text = contacto.telefono,
                    color = TextSecondary,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = contacto.email,
                    color = TextSecondary,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun elemento_contacto_preview()
{
    elemento_contacto(Contacto("Luis","98438323","add@gmail.com",null),{a,b->})
}

//Funcion para cargar una imagen desde una ruta interna de la app
@Composable
fun ImagenContactoDesdeRuta(fichero: File?,click_foto:()->Unit) {

    if (fichero != null) {

        Image( painter = rememberAsyncImagePainter(fichero),
            contentDescription = null,
            modifier = Modifier.clickable{
                //Hago click en la foto, debería lanzar el launcher
                click_foto()

            }.size(64.dp),
             ) }
    else {
        Image( painter = painterResource(R.drawable.avatar),
            contentDescription = null,
            modifier = Modifier.clickable{
                //Hago click en la foto
                click_foto()
            }.size(64.dp) ) }
}



//Funcion para crear un File
fun crearFile(context:Context):File{
    val nombreArchivo = "contacto_${System.currentTimeMillis()}.jpg"
    return File(context.filesDir, nombreArchivo)
}
/*Funcion para generar una URI, se deberá invocar antes de lanzar
* la intent de capturar imagen */
fun crearArchivoImagen(context: Context,file:File): Uri {

    return  FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )
}



