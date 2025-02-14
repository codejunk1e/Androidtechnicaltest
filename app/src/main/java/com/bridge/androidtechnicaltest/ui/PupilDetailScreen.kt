package com.bridge.androidtechnicaltest.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bridge.androidtechnicaltest.R
import com.bridge.androidtechnicaltest.domain.Pupil
import com.bridge.androidtechnicaltest.ui.components.PupilImage

@Composable
fun PupilDetailScreen(
    viewModel: PupilViewModel,
    onCancel: () -> Unit
) {
    val pupil by viewModel.pupil.collectAsState()

    var name by rememberSaveable { mutableStateOf(pupil?.name.orEmpty()) }
    var country by rememberSaveable { mutableStateOf(pupil?.country.orEmpty()) }
    var imageUrl by rememberSaveable { mutableStateOf(pupil?.image.orEmpty()) }
    var latitude by rememberSaveable { mutableStateOf(pupil?.latitude?.toString() ?: "") }
    var longitude by rememberSaveable { mutableStateOf(pupil?.longitude?.toString() ?: "") }
    var isEditingEnabled by rememberSaveable { mutableStateOf(pupil == null) }

    var nameError by rememberSaveable { mutableStateOf<String?>(null) }
    var countryError by rememberSaveable { mutableStateOf<String?>(null) }
    var imageUrlError by rememberSaveable { mutableStateOf<String?>(null) }

    fun validateFields(): Boolean {
        nameError = if (name.trim().length < 2) "Name must be at least 2 characters" else null
        countryError = if (country.trim().length < 2) "Country must be at least 2 characters" else null
        imageUrlError = if (imageUrl.trim().length < 11) "Image URL must be at least 11 characters" else null

        return nameError == null && countryError == null && imageUrlError == null
    }

    Scaffold(
        containerColor = Color.White,
        floatingActionButton = {
            if (pupil != null) {
                FloatingActionButton(
                    onClick = { isEditingEnabled = !isEditingEnabled },
                    containerColor = colorResource(R.color.colorPrimary)
                ) {
                    Text(if (isEditingEnabled) "Done" else "Edit", color = Color.White)
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PupilImage(imageUrl, name, modifier = Modifier.size(120.dp))

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    validateFields()
                },
                label = { Text("Name") },
                isError = nameError != null,
                singleLine = true,
                readOnly = !isEditingEnabled,
                modifier = Modifier.fillMaxWidth()
            )
            nameError?.let {
                Text(it, color = Color.Red, modifier = Modifier.align(Alignment.Start))
            }

            OutlinedTextField(
                value = country,
                onValueChange = {
                    country = it
                    validateFields()
                },
                label = { Text("Country") },
                isError = countryError != null,
                singleLine = true,
                readOnly = !isEditingEnabled,
                modifier = Modifier.fillMaxWidth()
            )
            countryError?.let {
                Text(it, color = Color.Red, modifier = Modifier.align(Alignment.Start))
            }

            OutlinedTextField(
                value = imageUrl,
                onValueChange = {
                    imageUrl = it
                    validateFields()
                },
                label = { Text("Image URL") },
                isError = imageUrlError != null,
                singleLine = true,
                readOnly = !isEditingEnabled,
                modifier = Modifier.fillMaxWidth()
            )
            imageUrlError?.let {
                Text(it, color = Color.Red, modifier = Modifier.align(Alignment.Start))
            }

            if (pupil != null && !isEditingEnabled) {
                OutlinedTextField(
                    value = latitude,
                    enabled = false,
                    onValueChange = { latitude = it },
                    label = { Text("Latitude") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = longitude,
                    enabled = false,
                    onValueChange = { longitude = it },
                    label = { Text("Longitude") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (isEditingEnabled) {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = onCancel) {
                        Text("Cancel")
                    }

                    Button(
                        onClick = {
                            if (validateFields()) {
                                val newPupil = Pupil(
                                    pupilId = pupil?.pupilId,
                                    name = name.trim(),
                                    country = country.trim(),
                                    image = imageUrl.trim()
                                )
                                if (pupil == null) {
                                    viewModel.savePupil(newPupil)
                                } else {
                                    viewModel.updatePupil(newPupil)
                                }
                                onCancel()
                            }
                        }
                    ) {
                        Text(if (pupil == null) "Add Pupil" else "Save changes")
                    }
                }
            }
        }
    }
}