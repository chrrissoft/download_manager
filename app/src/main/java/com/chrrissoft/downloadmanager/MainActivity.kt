package com.chrrissoft.downloadmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import com.chrrissoft.downloadmanager.utils.ComposeUtils.setBarsColors
import com.chrrissoft.downloadmanager.ui.ui.MainScreen
import com.chrrissoft.downloadmanager.ui.MainViewModel
import com.chrrissoft.downloadmanager.ui.components.app.App
import com.chrrissoft.downloadmanager.utils.Util.debug
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.stateFlow.collectAsState()
            App {
                setBarsColors()
                MainScreen(
                    state = state,
                    onEvent = { viewModel.handleEvent(it) }
                )
            }
        }
    }
}
