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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import com.test.lockconfiguration.ui.component.mySpinner
import com.test.lockconfiguration.ui.viewmodals.AccessControlConfigurationViewMobal


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun buildSearchBar() {
    var text by remember { mutableStateOf("") } // Query for SearchBar

    var active by remember { mutableStateOf(false) } // Active state for SearchBar

    SearchBar(modifier = Modifier.fillMaxWidth(),
        query = text,
        onQueryChange = {
            text = it
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

@ExperimentalMaterial3Api
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    shape: Shape = SearchBarDefaults.inputFieldShape,
    colors: SearchBarColors = SearchBarDefaults.colors(),
    tonalElevation: Dp = SearchBarDefaults.Elevation,
    windowInsets: WindowInsets = SearchBarDefaults.windowInsets,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable ColumnScope.() -> Unit,
){
    var text by remember { mutableStateOf("") }
    TextField(
        value = text, onValueChange = {text = it},
        placeholder = { Text("Search Parameter") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {

           Log.d("SearchBar", "onSearch $text")
        }),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 5.dp, bottom = 5.dp, end = 15.dp)
            .border(1.dp, Color.Black, RectangleShape),
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
    )
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
val gradientEdit =
    Brush.verticalGradient(listOf(Color(0xFFF3DD4E), Color(0xAA9CF70B)))

val gradientSave =
    Brush.verticalGradient(listOf(Color(0xAA9CF70B), Color(0xFFEC6966), Color(0xFF7A706F), Color(0xFFF3DD4E)))

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
                accm?.javaClass?.let {
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
                accm?.let {
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

                accm?.let {
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

/*
@Suppress("ModifierParameter")
@Composable
fun DropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    properties: PopupProperties = PopupProperties(focusable = true),
    content: @Composable ColumnScope.() -> Unit
){

}
*/



//@Composable
//private fun setAngle(angle : Int){
//    LockAngle().setValue(value = angle)
//}