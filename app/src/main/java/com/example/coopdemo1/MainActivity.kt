package com.example.coopdemo1

/*
Name: Hanz Samonte
Date: 03/04/2025
Course: Mobile App Development
Description: Android Shared Learning Coop Demo #1 and #2:
#1 - Color Picker App using Jetpack Compose Layout and Animation
#2 - Data Display App using ViewModel and Retrofit
 */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.coopdemo1.screens.ColorPickerScreen
import com.example.coopdemo1.screens.DataDisplayScreen
import com.example.coopdemo1.ui.theme.CoopDemo1Theme
import com.example.coopdemo1.viewmodel.ColorPickerViewModel
import com.example.coopdemo1.viewmodel.CharacterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoopDemo1Theme {
                // Run the MainApp composable App
                MainApp()
            }
        }
    }
}

/**
 * The main entry point for the App.
 * Sets up the navigation and view models.
 */
@Composable
fun MainApp() {
    // Create a navigation controller
    val navController = rememberNavController()
    // Create the view models
    val colorPickerViewModel: ColorPickerViewModel = viewModel()
    val characterViewModel: CharacterViewModel = viewModel()

    // Set up the navigation host
    NavHost(navController = navController, startDestination = "color_picker") {
        // Add the composable screens
        composable("color_picker") { ColorPickerScreen(navController, colorPickerViewModel) }
        composable("data_display") {
            DataDisplayScreen(
                navController = navController,
                colorPickerViewModel = colorPickerViewModel,
                characterViewModel = characterViewModel
            )
        }
    }
}
