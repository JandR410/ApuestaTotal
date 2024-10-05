package com.example.apuestatotal.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppDropdownTextField(
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    hasError: Boolean = false,
    errorText: String = "",
    shape: Shape = RoundedCornerShape(12.dp)
) {
    var expanded by remember { mutableStateOf(false) }
    var displayText by remember { mutableStateOf(selectedOption) }
    var currentOptions by remember { mutableStateOf(options) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = displayText,
            onValueChange = { input ->
                displayText = input
                onOptionSelected(input)
                if (input.isNotEmpty() && input !in currentOptions) {
                    currentOptions = currentOptions + input
                }
            },
            label = {
                Text(
                    text = labelText,
                    color = Black
                )
            },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded = true
                },
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            },
            shape = shape,
            supportingText = {
                if (hasError) Text(
                    text = errorText,
                    color = Color(0x00FF0000),
                    modifier = Modifier.offset(x = -(8).dp)
                )
            },
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier.fillMaxWidth()
        ) {
            currentOptions.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        displayText = option
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppDropdownTextField() {
    var selectedOption by remember { mutableStateOf("") }
    val options = listOf("Type 1", "Type 2", "Type 3")

    AppDropdownTextField(
        selectedOption = selectedOption,
        options = options,
        onOptionSelected = { selectedOption = it },
        labelText = "drop"
    )
}