package com.karakoca.baseproject.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.karakoca.baseproject.di.getSharedModules
import com.karakoca.baseproject.ui.MyApplicationTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatformTools

@Composable
@Preview
fun App() {
    if (KoinPlatformTools.defaultContext().getOrNull() == null)
        startKoin { modules(getSharedModules()) }
    KoinContext {
        MyApplicationTheme {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MainNavController()
            }
        }
    }
}