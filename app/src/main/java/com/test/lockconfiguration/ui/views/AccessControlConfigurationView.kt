package com.test.lockconfiguration.ui.views

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.lockconfiguration.modal.ConfigurationModal
import com.test.lockconfiguration.ui.component.SearchField
import com.test.lockconfiguration.ui.component.mySpinner
import com.test.lockconfiguration.ui.theme.gradientEdit
import com.test.lockconfiguration.ui.theme.gradientSave
import com.test.lockconfiguration.ui.viewmodals.AccessControlConfigurationViewMobal


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun buildSearchBar(searchQuery: MutableState<String>) {
    var text by remember { mutableStateOf("") } // Query for SearchBar

    var active by remember { mutableStateOf(false) } // Active state for SearchBar

    SearchField(modifier = Modifier.fillMaxWidth(),
        query = text,
        onQueryChange = {
            text = it
            searchQuery.value = it
        },
        onSearch = {
            active = false
        },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = {
            Text(text = "Enter your query")
        },
        trailingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }) {


    }
}

@Composable
fun PropertyTitleBarView(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(Color.Black),

        ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(1f)
                .padding(top = 0.dp, start = 0.dp)
                .weight(0.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Primary",
                color = Color.White,
                textAlign = TextAlign.Center,
                )
        }
        Column(
            modifier = Modifier

                .fillMaxHeight()
                .fillMaxWidth(1f)
                .padding(top = 0.dp, start = 0.dp)
                .weight(0.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally


        ) {
            Text(
                text = "Secondary",
                color = Color.White,
                textAlign = TextAlign.Center,


                )
        }
    }
}
@Composable
inline fun <reified T>PropertyView(accm: ConfigurationModal<T>, activity: ComponentActivity) {

    val _viewMobal: AccessControlConfigurationViewMobal by activity.viewModels()
    val viewModel = remember { _viewMobal }
    val isEdit = remember { mutableStateOf(false) }
    val value = accm.getValue(viewModel.sharedPreferences)
    val configurationOptions = accm.getValues()
    val (selectedRoom, setSelectedRoom) = remember { mutableStateOf("${listOf(value)[0]}") }
    //val statVal = accm.setValue(value = selectedRoom , sharedPreferences = viewModel.sharedPreferences)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .background(Color.Blue)
                .height(75.dp)
                .fillMaxWidth(1f)
                .padding(top = 0.dp, start = 0.dp)
                .weight(0.9f)


        ) {

            Row(
                Modifier
                    .background(Color(0xAAA5E73B))
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(1f),
                horizontalArrangement = Arrangement.Absolute.Center,
                verticalAlignment = Alignment.CenterVertically

            ) {
                accm.javaClass?.let {
                    Text(
                        text = it.simpleName,
                        color = Color(0xFFFDD835),
                        textAlign = TextAlign.Center,
                        modifier = Modifier

                    )
                }
            }
            Row(
                Modifier
                    .background(Color(0xFFF3DD4E))
                    .fillMaxHeight(1f)
                    .fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically


            ) {
                accm.let {
                    if(isEdit.value){
                        mySpinner(
                            "",
                            choices = configurationOptions, // listOf("H120", "H153", "H155"),
                            "${accm.getValue(viewModel.sharedPreferences)}", ){
                            ///if(T::class.java.typeName)
                            val tV = when (T::class) {
                                Int::class -> it.toInt()
                                Double::class -> it.toDouble()
                                String::class -> it as T
                                // add other types here if need
                                else -> throw IllegalStateException("Unknown Generic Type ${T::class}")
                            }
                            accm.setValue(value = tV as T, sharedPreferences = viewModel.sharedPreferences)
                        }
                    }else{

                        Text(
                            text = "${accm.getValue(viewModel.sharedPreferences)}",
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .weight(0.5f)
                                .padding(start = 10.dp)
                        )
                    }

                }

                accm.let {
                    Text(
                        text = "${ it.getDefaultValue() }",
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(end = 10.dp)
                    )
                }

            }



        }

        Button(
            onClick = {
                Log.d("TAG", "onClick")
                 //accm.setValue(value = selectedRoom as T, sharedPreferences = viewModel.sharedPreferences)
                //statVal.value = selectedRoom
                isEdit.value = !isEdit.value


                      },
            colors = ButtonDefaults.buttonColors(Color(0x88777A79)),
            modifier = Modifier
                .height(75.dp)
                .background(if (isEdit.value) gradientSave else gradientEdit)
                .weight(0.12f),
            shape = RectangleShape,


        ){
//            Image(painter = icon, contentDescription = null)
//            Spacer(modifier = Modifier.width(8.dp)) // Adjust spacing
          //  Text(text, fontSize = 40.sp)
            Text(
                text = ">",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF615C46),

            )
        }
    }
}