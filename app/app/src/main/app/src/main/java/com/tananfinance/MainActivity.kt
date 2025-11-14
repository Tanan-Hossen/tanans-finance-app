package com.tananfinance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tananfinance.ui.theme.TanansFinanceTheme
import androidx.compose.runtime.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.tananfinance.ui.screens.PinScreen
import com.tananfinance.ui.screens.HomeScreen
import com.tananfinance.viewmodel.FinanceViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TanansFinanceTheme {
                val vm: FinanceViewModel = viewModel(factory = FinanceViewModel.Factory(applicationContext))
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    var unlocked by remember { mutableStateOf(false) }
                    if (!unlocked) {
                        PinScreen(onUnlocked = { unlocked = true })
                    } else {
                        val nav = rememberNavController()
                        HomeScreen(viewModel = vm)
                    }
                }
            }
        }
    }
}
