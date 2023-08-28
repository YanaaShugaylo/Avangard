package com.avangard.bratstvo.base.root.data

import android.content.Context
import android.net.Uri
import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.base.root.domain.BaseRequestsRepository
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

import okhttp3.MultipartBody
import android.util.Log
import com.avangard.bratstvo.base.root.domain.MainActivityInteractionsHelper
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

class BaseRequestsRepositoryImpl(
    private val api: BaseRequestsApi,
    private val appDispatchers: AppDispatchers,
    private val context: Context
) : BaseRequestsRepository {

    override suspend fun uploadFile(fileUri: Uri, appSection: String) = withContext(appDispatchers.network) {
        MainActivityInteractionsHelper.setLoaderVisibility(isVisible = true)
        var fileUuid: String = ""

        try {
            val filePath = getFilePathFromUri(fileUri)
            val file = File(filePath)

            val requestFile: RequestBody = file
                .asRequestBody(context.contentResolver.getType(fileUri)?.toMediaTypeOrNull())

            val body: MultipartBody.Part = MultipartBody.Part.createFormData("file", file.name, requestFile)

            val description = appSection.toRequestBody(MultipartBody.FORM)

            val result = api.uploadImage(description, body)
            fileUuid = result.data.uuid
        } catch (e: Exception) {
            Log.i("myLog", "uploadFile, e = $e")
        }

        MainActivityInteractionsHelper.setLoaderVisibility(isVisible = false)

        fileUuid
    }

    private fun getFilePathFromUri(uri: Uri): String {
        val fileName = "temp"
        val file = File(context.externalCacheDir, fileName)
        file.createNewFile()
        FileOutputStream(file).use { outputStream ->
            context.contentResolver.openInputStream(uri).use { inputStream ->
                copy(inputStream!!, outputStream) //Simply reads input to output stream
                outputStream.flush()
            }
        }
        return file.absolutePath
    }

    private fun copy(source: InputStream, target: OutputStream) {
        val buf = ByteArray(8192)
        var length: Int
        while (source.read(buf).also { length = it } > 0) {
            target.write(buf, 0, length)
        }
    }
}