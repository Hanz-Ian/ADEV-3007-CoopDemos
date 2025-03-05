package com.example.coopdemo1

/*
Name: Hanz Samonte
Date: 03/04/2025
Course: Mobile App Development
Description: Android Shared Learning Coop Demo #1 - Color Picker App using Jetpack Compose Layout and Animation
 */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coopdemo1.ui.theme.CoopDemo1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoopDemo1Theme {
                // Run the ColorPickerScreen composable App
                ColorPickerScreen()
            }
        }
    }
}

// The screen composable app to display in our main activity
@Composable
fun ColorPickerScreen() {
    // State to hold the selected color
    var selectedColor by remember { mutableStateOf(Color.White) }
    // State to show/hide the color list
    var showColorList by remember { mutableStateOf(false) }
    // State to hold the background color
    var backgroundColor by remember { mutableStateOf(Color.White) }

    // Animated color transition
    val animatedColor by animateColorAsState(
        targetValue = backgroundColor,
        animationSpec = tween(durationMillis = 1000) // 1 second animation
    )

    // List of colors to choose from
    val colors = listOf(Color.Red,
                        Color.Green,
                        Color.Blue,
                        Color.Yellow,
                        Color.Cyan,
                        Color.Magenta)

    // The whole screen (Box)
    Box(
        modifier = Modifier
            .fillMaxSize() // Fill the whole screen
            .background(animatedColor), // Background color changes with animation
        contentAlignment = Alignment.Center // Center the contents
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Color display box
            Box(
                modifier = Modifier
                    .size(100.dp) // Size of the box
                    .background(selectedColor) // The color of the box depends on the selected color
                    .border(2.dp, Color.Black) // Border around the box
                    .clickable { showColorList = !showColorList } // Show/hide color list when clicked
            )

            // Spacer to add some space between the color display box and the button
            Spacer(modifier = Modifier.height(20.dp))

            // Button to change background color when clicked
            Button(onClick = { backgroundColor = selectedColor }) {
                Text("Change Background Color")
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
                                    selectedColor = color
                                    showColorList = false
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
