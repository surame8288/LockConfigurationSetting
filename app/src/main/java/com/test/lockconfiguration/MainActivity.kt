package com.test.lockconfiguration

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.lockconfiguration.modal.AccessControlConfigurationModal
import com.test.lockconfiguration.modal.LockConfiguration
import com.test.lockconfiguration.ui.theme.LockConfigurationTheme
import com.test.lockconfiguration.ui.viewmodals.AccessControlConfigurationViewMobal
import com.test.lockconfiguration.ui.views.PropertyTitleBarView
import com.test.lockconfiguration.ui.views.PropertyView
import com.test.lockconfiguration.ui.views.buildSearchBar

class MainActivity : ComponentActivity() {
    private val viewMobal: AccessControlConfigurationViewMobal by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // viewMobal.getAccessControlConfigurationData()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                LockConfigurationTheme {
                    ScaffoldWithTopBar(bindView())
                }
            }

        }

    }


    @SuppressLint("SuspiciousIndentation")
    @Composable
    fun bindView(): @Composable (PaddingValues) -> Unit {
        val searchQuery = remember { mutableStateOf("") }
        val dataAccessControlConfiguration = remember {
            mutableStateOf(AccessControlConfigurationModal())
        }
        viewMobal.getAccessControlConfigurationData_Composable(data = dataAccessControlConfiguration)
        return { it ->
            Column(
                modifier = Modifier
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(8.dp))
                buildSearchBar(searchQuery)
                Spacer(modifier = Modifier.padding(15.dp))
                PropertyTitleBarView()

                if (dataAccessControlConfiguration.value.lockAngle != null) {
                    LazyColumn {
                        val configList = parseLockConfigurations(dataAccessControlConfiguration)
                        items(configList.size) {
                            PropertyView(
                                accm = configList[it],
                                activity = this@MainActivity
                            )
                        }
                    }
                } else {
                    CircularProgressIndicator()
                }
            }
        }
    }

    private fun parseLockConfigurations(dataAccessControlConfiguration: MutableState<AccessControlConfigurationModal>) =
        dataAccessControlConfiguration.value::class.java.declaredFields.fold(
            mutableListOf<LockConfiguration<*>>()
        ) { list, conf ->
            try {
                conf.isAccessible = true
                val v = conf.get(dataAccessControlConfiguration.value)
                if (v is LockConfiguration<*>) {
                    list.add(v)
                }
                list
            } catch (e: IllegalArgumentException) {
                mutableListOf()
            }
        }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScaffoldWithTopBar(content: @Composable() (PaddingValues) -> Unit) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Lock Parameters",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.ArrowBack, "backIcon")
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.White,
                    ),
                )
            }, content = content
        )
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
//    LockConfigurationTheme {
//        PropertyView("Android", "value", "defaultValue")

//    }
//    PropertyView("Android", "value", "defaultValue")
//    PropertyTitleBarView()
        val searchQuery = remember { mutableStateOf("") }
        buildSearchBar(searchQuery)
    }
}