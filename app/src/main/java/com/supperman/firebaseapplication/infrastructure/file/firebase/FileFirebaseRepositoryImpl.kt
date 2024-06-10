package com.supperman.firebaseapplication.infrastructure.file.firebase

import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import com.supperman.firebaseapplication.infrastructure.file.FileRepository
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime

class FileFirebaseRepositoryImpl : FileRepository {
    override suspend fun SaveFile(fileName: String, uri: Uri): String? {
        TODO("Not yet implemented")
    }

}