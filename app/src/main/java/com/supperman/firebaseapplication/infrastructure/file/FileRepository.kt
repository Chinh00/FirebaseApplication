package com.supperman.firebaseapplication.infrastructure.file

import android.net.Uri

interface FileRepository {
    suspend fun SaveFile(fileName: String, uri: Uri): String?;
}