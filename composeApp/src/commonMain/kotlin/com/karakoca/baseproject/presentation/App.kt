package com.karakoca.baseproject.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
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
                    .fillMaxSize()
                    .systemBarsPadding()
                    .imePadding(),
                color = MaterialTheme.colorScheme.background
            ) {
                MainNavController()
            }
        }
    }
}