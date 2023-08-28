package com.avangard.bratstvo.base.coroutines

import kotlinx.coroutines.Dispatchers

class AppDispatchers {
    val ui = Dispatchers.Main
    val storage = Dispatchers.IO
    val network = Dispatchers.IO
    val computing = Dispatchers.Default
}