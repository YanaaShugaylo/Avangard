package com.avangard.bratstvo.base.root.domain

import android.net.Uri

interface BaseRequestsRepository {

    suspend fun uploadFile(fileUri: Uri, appSection: String): String
}