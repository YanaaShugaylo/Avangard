package com.avangard.bratstvo.base.root.domain

import android.net.Uri

class UploadFileUseCase(private val baseRequestsRepository: BaseRequestsRepository) {

    suspend operator fun invoke(fileUri: Uri, type: String): String = baseRequestsRepository.uploadFile(fileUri, type)
}