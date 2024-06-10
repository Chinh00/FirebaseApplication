package com.supperman.firebaseapplication.application.components

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import java.time.LocalDateTime
import com.supperman.firebaseapplication.R
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FileTextField( fileName: String, onChange: (String) -> Unit) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    // Define the contract for picking a file
    val pickFile = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                onChange(uri.toString())
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { pickFile.launch("*/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Chọn logo")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

