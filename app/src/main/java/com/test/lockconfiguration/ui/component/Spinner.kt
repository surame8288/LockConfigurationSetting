package com.test.lockconfiguration.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun mySpinner(
    label: String,
    choices: List<String>,
    selectedOption: String,
    setSelected: @Composable (selected: String) -> Unit
) {
    var spinnerText by remember { mutableStateOf(selectedOption) }
    var my_expanded by remember { mutableStateOf(false) }

    if(spinnerText.length>0){
        setSelected(spinnerText)
    }
    Row {
        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .wrapContentWidth(),
            text = label,
            fontWeight = FontWeight.Normal
        )
        Box(
            Modifier
                .width(150.dp)
                .border(width = 1.dp, color = Color.Black)
        ) {
            Row(Modifier
                .padding(start = 12.dp)
                .clickable {
                    my_expanded = !my_expanded
                }
                .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = spinnerText,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
                DropdownMenu(expanded = my_expanded, onDismissRequest = { my_expanded = false }) {
                    choices.forEach { a_choice ->
                        DropdownMenuItem(
                            text = { Text(text = a_choice)

                                   },
                            onClick = {
                                my_expanded = false
                                spinnerText = a_choice
//                                setSelected(spinnerText)
                            })

                    }
                }
            }
        }
    }
}