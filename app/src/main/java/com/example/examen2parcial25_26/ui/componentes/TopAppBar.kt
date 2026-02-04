package com.example.examen2parcial25_26.ui.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.examen2parcial25_26.R
import com.example.examen2parcial25_26.ui.theme.BlueOnPrimary
import com.example.examen2parcial25_26.ui.theme.BluePrimary
import com.example.examen2parcial25_26.ui.theme.ToolbarTitleStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun miTopAppBar()
{
    var menu_expandido by remember { mutableStateOf(false) }
    TopAppBar(
        navigationIcon = {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(70.dp)
                    .padding(start = 16.dp)
            )}
        ,
      title = {Box(modifier=Modifier.fillMaxWidth().padding(start = 32.dp), contentAlignment = Alignment.CenterStart){ Text("GEST-CONTACT", style = ToolbarTitleStyle)}
      },
        actions = {
            IconButton(colors = IconButtonDefaults.iconButtonColors(contentColor = BlueOnPrimary),
                onClick = {menu_expandido=!menu_expandido}
            ) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
            }
            DropdownMenu(menu_expandido, onDismissRequest = {menu_expandido=false}) {
                DropdownMenuItem(text={Text("Guardar contactos")}, trailingIcon = {Icon(imageVector = Icons.Default.Save, contentDescription = "")}, onClick = {
                    //Guardamos los contactos,
                    //Elevamos el click hacia arriba

                })
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BluePrimary,
            titleContentColor = BlueOnPrimary
        )
    )
}