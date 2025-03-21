package com.example.coopdemo1.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.coopdemo1.viewmodel.ColorPickerViewModel


/**
 * The screen composable app to display in the main activity.
 * @param navController the navigation controller to handle navigation.
 * @param viewModel the view model for managing color picker state.
 */
@Composable
fun ColorPickerScreen(navController: NavController, viewModel: ColorPickerViewModel) {
    // viewModel state to hold the selected color
    val selectedColor by viewModel.selectedColor.collectAsState()
    // viewModel state to show/hide the color list
    val showColorList by viewModel.showColorList.collectAsState()
    // viewModel state to hold the background color
    val backgroundColor by viewModel.backgroundColor.collectAsState()

    // Animated color transition
    val animatedColor by animateColorAsState(
        targetValue = backgroundColor,
        animationSpec = tween(durationMillis = 1000) // 1 second animation
    )

    // List of colors to choose from
    val colors = listOf(
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow,
        Color.Cyan,
        Color.Magenta)

    // The whole screen container (Box)
    Box(
        modifier = Modifier
            .fillMaxSize() // Fill the whole screen
            .background(color = animatedColor), // Background color changes with animation
        contentAlignment = Alignment.Center // Center the contents
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Color display box
            Box(
                modifier = Modifier
                    .size(100.dp) // Size of the box
                    .background(color = selectedColor) // The color of the box depends on the selected color
                    .border(2.dp, Color.Black) // Border around the box
                    .clickable { viewModel.toggleColorList() } // Show/hide color list when clicked
            )

            // Spacer to add some space between the color display box and the button
            Spacer(modifier = Modifier.height(20.dp))

            // Button to change background color when clicked
            Button(onClick = { viewModel.setBackgroundColor(selectedColor) }) {
                Text("Change Background Color")
            }

            // Spacer to add some space between the button and the next button
            Spacer(modifier = Modifier.height(20.dp))

            // Button to navigate to the Data Display Screen
            Button(onClick = { navController.navigate("data_display") }) {
                Text("Go to Data Display Screen")
            }

            // Color list with sliding animation
            AnimatedVisibility(
                visible = showColorList, // Show the color list if true
                enter = expandVertically(animationSpec = tween(500)), // Slide down animation (show)
                exit = shrinkVertically(animationSpec = tween(500)) // Slide up animation (hide)
            ) {
                // Column to arrange the colors in a list positioned vertically
                Column {
                    // For each color in the colors list val, create a box with the color
                    colors.forEach { color ->
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(color) // The color
                                .border(2.dp, Color.Black) // Border around the box
                                .clickable { // When clicked, set the selected color and hide the color list
                                    viewModel.setSelectedColor(color)
                                    viewModel.toggleColorList()
                                }
                        )
                        // Use a Spacer to add some space between the color boxes during iteration
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}