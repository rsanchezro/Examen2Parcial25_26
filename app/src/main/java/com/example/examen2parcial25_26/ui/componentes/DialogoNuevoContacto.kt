package com.example.examen2parcial25_26.ui.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.examen2parcial25_26.modelo.Contacto
import com.example.examen2parcial25_26.ui.theme.BlueOnPrimary
import com.example.examen2parcial25_26.ui.theme.BluePrimary
import com.example.examen2parcial25_26.ui.theme.BlueSurface
import com.example.examen2parcial25_26.ui.theme.TextPrimary
import com.example.examen2parcial25_26.ui.theme.TextSecondary

@Composable
fun DialogNuevoContacto(
    onDismiss: () -> Unit,
    onGuardar: (Contacto) -> Unit
) {

    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {

        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = BlueSurface),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                //  Título elegante
                Text(
                    text = "Nuevo contacto",
                    color = TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    TextButton(onClick = onDismiss) {
                        Text("Cancelar", color = TextSecondary)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            onGuardar(
                                Contacto(
                                    nombre,
                                    telefono,
                                    email,
                                    foto = null
                                )
                            )
                        },
                        enabled = nombre.isNotBlank() && telefono.isNotBlank(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BluePrimary,
                            contentColor = BlueOnPrimary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text("Guardar")
                    }
                }
            }
        }
    }
}
