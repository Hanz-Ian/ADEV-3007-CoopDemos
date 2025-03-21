package com.example.coopdemo1.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel for managing color picker state.
 * Handles the selected color, background color, and visibility of the color list.
 */
class ColorPickerViewModel : ViewModel() {
    // MutableStateFlow to hold the selected color
    private val _selectedColor = MutableStateFlow(Color.White)
    // Public StateFlow to observe the selected color
    val selectedColor: StateFlow<Color> = _selectedColor

    // MutableStateFlow to hold the background color
    private val _backgroundColor = MutableStateFlow(Color.White)
    // Public StateFlow to observe the background color
    val backgroundColor: StateFlow<Color> = _backgroundColor

    // Mutable state flow to hold the visibility state of the color list
    private val _showColorList = MutableStateFlow(false)
    // Public state flow to expose the visibility state of the color list
    val showColorList: StateFlow<Boolean> = _showColorList

    /**
     * Sets the selected color.
     * @param color the color to set as selected
     */
    fun setSelectedColor(color: Color) {
        _selectedColor.value = color
    }

    /**
     * Sets the background color.
     * @param color the color to set as background
     */
    fun setBackgroundColor(color: Color) {
        _backgroundColor.value = color
    }

    /**
     * Toggles the visibility of the color list.
     */
    fun toggleColorList() {
        _showColorList.value = !_showColorList.value
    }
}