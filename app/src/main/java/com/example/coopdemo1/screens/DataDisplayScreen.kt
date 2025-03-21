package com.example.coopdemo1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.coopdemo1.viewmodel.CharacterViewModel
import com.example.coopdemo1.viewmodel.ColorPickerViewModel
import kotlinx.coroutines.launch

/**
 * Displays character data and allows navigation back to the ColorPickerScreen.
 * @param navController the navigation controller to handle navigation.
 * @param colorPickerViewModel the view model for managing color picker state.
 * @param characterViewModel the view model for managing character data.
 */
@Composable
fun DataDisplayScreen(
    navController: NavController?,
    colorPickerViewModel: ColorPickerViewModel,
    characterViewModel: CharacterViewModel
) {
    // Observe the background color state
    val backgroundColor by colorPickerViewModel.backgroundColor.collectAsState()
    // Observe the character state
    val character by characterViewModel.character.collectAsState()
    // Create a coroutine scope
    val coroutineScope = rememberCoroutineScope()

    // Main screen container
    Box(
        modifier = Modifier
            .fillMaxSize() // Fill the entire screen
            .background(color = backgroundColor), // Set the background color
        contentAlignment = Alignment.Center // Center the contents
    ) {
        // Column to arrange elements vertically
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Center the elements horizontally
            verticalArrangement = Arrangement.Center // Center the elements vertically
        ) {
            // Display the character image and name if available
            character?.let {
                Image(
                    painter = rememberAsyncImagePainter(it.image), // Load the character image
                    contentDescription = it.name, // Set the content description
                    modifier = Modifier.size(200.dp), // Set the size of the image
                    contentScale = ContentScale.Crop // Crop the image
                )
                Spacer(modifier = Modifier.height(16.dp)) // Add some space
                Text(text = it.name) // Display the character name
            }

            // Add some space
            Spacer(modifier = Modifier.height(16.dp))

            // Button to refresh the character data
            Button(onClick = {
                coroutineScope.launch {
                    val randomId = (1..671).random() // Generate a random character ID
                    characterViewModel.fetchCharacter(randomId) // Fetch the character data
                }
            }) {
                Text("Refresh") // Display the button text
            }

            // Add some space
            Spacer(modifier = Modifier.height(16.dp))

            // Button to navigate back to the ColorPickerScreen
            Button(onClick = { navController?.navigate("color_picker") }) {
                Text("Back to Color Picker Screen")
            }
        }
    }
}