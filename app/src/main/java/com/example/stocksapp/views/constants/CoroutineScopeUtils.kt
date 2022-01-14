package com.example.stocksapp.views.constants

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoroutineScopeUtils {
    fun runTaskOnCoroutineMain(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch { work() }

    fun runTaskOnCoroutineBackground(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.IO).launch { work() }
}