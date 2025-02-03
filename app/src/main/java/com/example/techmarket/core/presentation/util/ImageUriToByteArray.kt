package com.example.techmarket.core.presentation.util

import android.content.Context
import android.net.Uri
import android.util.Log

fun Context.uriToByteArrayAndMimeType(uri: Uri): Pair<ByteArray?, String?> {
    return try {
        val mimeType = contentResolver.getType(uri) // Retrieve MIME type
        val byteArray = contentResolver.openInputStream(uri)?.use { it.readBytes() }
        byteArray to mimeType
    } catch (e: Exception) {
        Log.d("uriToByteArray", "Error: ${e.message}")
        null to null
    }
}
