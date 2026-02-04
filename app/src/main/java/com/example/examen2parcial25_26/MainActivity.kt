package com.example.examen2parcial25_26

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.examen2parcial25_26.data.ProveedorContacto
import com.example.examen2parcial25_26.data.ProveedorContactoDispositivo
import com.example.examen2parcial25_26.navegacion.Ruta
import com.example.examen2parcial25_26.navegacion.miNavHost
import com.example.examen2parcial25_26.ui.componentes.BottomNavItem
import com.example.examen2parcial25_26.ui.componentes.miTopAppBar
import com.example.examen2parcial25_26.ui.componentes.mibottombar
import com.example.examen2parcial25_26.ui.theme.BlueAccent
import com.example.examen2parcial25_26.ui.theme.BlueOnPrimary
import com.example.examen2parcial25_26.ui.theme.Examen2Parcial25_26Theme
import com.example.examen2parcial25_26.viewmodel.ContactosViewModel
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Necesario para poder cargar de un fichero json
        ProveedorContacto.inicializar(this)
        //Necesario para cargar los contactos del dispositivo
        ProveedorContactoDispositivo.inicializar(this)
        //Paso los contactos de assets a FilesDir
        copiarContactosDesdeAssets(this)
        setContent {
            Examen2Parcial25_26Theme {
                    pantallaPrincipal()
                }
            }
        }
    fun copiarContactosDesdeAssets(context: Context) {
        try {
            val archivoDestino = File(context.filesDir, "contactos.json")

            // Si ya existe, lo elimino, para de esta forma con añadir los ficheros
            //al json de asset ya se actualizan los contactos
            if (archivoDestino.exists())
            {
                archivoDestino.delete()


            }

            // Abrimos el archivo desde assets
            val inputStream = context.assets.open("contactos.json")

            // Leemos todo el contenido
            val contenido = inputStream.bufferedReader().use { it.readText() }

            // Lo escribimos en filesDir
            archivoDestino.writeText(contenido)

            Log.i("copiarContactos", "contactos.json copiado a filesDir correctamente")

        } catch (e: Exception) {
            Log.e("copiarContactos", "Error copiando contactos: ${e.message}")
        }
    }


    }

@Composable
fun pantallaPrincipal()
{
    val contexto=LocalContext.current
    //Instancio el viewModel
    val miviewmodel: ContactosViewModel= viewModel()
    //Definimos el controlador de navegación
    val controlador_navegacion= rememberNavController()
    //Definimos el elemento seleccionado en el bottombar
    var elemento_seleccionado by remember { mutableStateOf<Ruta>(Ruta.ContactosApp) }



    Scaffold (modifier = Modifier.fillMaxSize().statusBarsPadding(),
        topBar = { miTopAppBar() },
        bottomBar = { mibottombar(elemento_seleccionado,controlador_navegacion){elemento_seleccionado=it} },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Acción: añadir nuevo contacto
                },
                containerColor = BlueAccent,
                contentColor = BlueOnPrimary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir contacto"
                )
            }
        },

        floatingActionButtonPosition = FabPosition.End){
        miNavHost(Modifier.padding(it),controlador_navegacion,miviewmodel.contactos,
            onclick_cambiarfoto = { contacto ->
                //Obtengo un fichero para guardar la imagen
                file_imagen=crearArchivoImagen(contexto,contacto.nombre)

                //Aqui abro la intent de la camara, pasandole la uri de la imagen
                launcher_foto.launch(crearUriImagen(contexto, file_imagen!!))

                miviewmodel.actualizarContacto(contacto,contacto.copy(foto = file_imagen!!.absolutePath))


        })

    }
}




