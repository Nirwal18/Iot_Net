package com.nirwal.iotnet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nirwal.iotnet.ui.navigation.SetupNavGraph
import com.nirwal.iotnet.ui.theme.IotNetTheme

class MainActivity : ComponentActivity() {
    private lateinit var  _navController:NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IotNetTheme {
                _navController = rememberNavController()
                SetupNavGraph(navController=_navController)
            }
        }
    }
}

