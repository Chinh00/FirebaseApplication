package com.supperman.firebaseapplication.application.modules.department._detail


import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.storage.FirebaseStorage
import com.supperman.firebaseapplication.R
import com.supperman.firebaseapplication.application.components.BaseScreen
import com.supperman.firebaseapplication.application.components.Dropdown
import com.supperman.firebaseapplication.application.components.DropdownConfig
import com.supperman.firebaseapplication.application.components.FileTextField
import com.supperman.firebaseapplication.application.components.LoadingButton
import com.supperman.firebaseapplication.application.modules.department._create.DepartmentCreateViewModel
import com.supperman.firebaseapplication.application.modules.department._list.DepartmentListViewModel
import com.supperman.firebaseapplication.infrastructure.utils.state.ApiState
import kotlinx.coroutines.flow.collect
import java.io.InputStream

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DepartmentDetailScreen (
    navHostController: NavHostController
) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments
    val id = arguments?.getString("id")
    val departmentListViewModel: DepartmentListViewModel = hiltViewModel()
    val departmentDetailViewModel: DepartmentDetailViewModel = hiltViewModel()
    val department = departmentDetailViewModel.department.collectAsState()
    Log.d("Chinhaaa", department.value.second.name.toString())
    val name = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    val website = remember {
        mutableStateOf("")
    }
    val logo = remember {
        mutableStateOf("")
    }
    val address = remember {
        mutableStateOf("")
    }
    val phone = remember {
        mutableStateOf("")
    }
    val departmentId = remember {
        mutableStateOf("")
    }
    val fileName = remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        departmentListViewModel.HandleGetList()
        if (id != null) {
            departmentDetailViewModel.HandleGetDepartmentDetail(id)
        }
    }

    LaunchedEffect(key1 = Unit) {

    }
    LazyColumn (modifier = Modifier.apply {
        fillMaxWidth()
    }) {
        item {
            TextField(value = name.value, onValueChange = {name.value = it}, label = { Text(text = "Tên đơn vị")}, modifier = Modifier
                .fillMaxSize()
                .padding(10.dp))
        }
        item {
            TextField(value = email.value, onValueChange = {email.value = it}, label = { Text(text = "Email")}, modifier = Modifier
                .fillMaxSize()
                .padding(10.dp))
        }
        item {
            TextField(value = website.value, onValueChange = {website.value = it}, label = { Text(text = "Website")}, modifier = Modifier
                .fillMaxSize()
                .padding(10.dp))
        }
        item {
            TextField(value = address.value, onValueChange = {address.value = it}, label = { Text(text = "Địa chỉ")}, modifier = Modifier
                .fillMaxSize()
                .padding(10.dp))
        }
        item {

            TextField(value = phone.value, onValueChange = {phone.value = it}, label = { Text(text = "Số điện thoại")}, modifier = Modifier
                .fillMaxSize()
                .padding(10.dp))
        }
        item {

            FileTextField(fileName = fileName.value) {
                fileName.value = it
            }

        }
        item {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),Arrangement.Center, Alignment.CenterVertically ) {
                if (fileName.value != "") {
                    ImageFromUri(contentResolver = context.contentResolver, uri = fileName.value)
                }
            }

        }
        item {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),Arrangement.Center, Alignment.CenterVertically ) {
                Dropdown(mCities = departmentListViewModel.list.collectAsState().value.map { DropdownConfig(it.first, it.second.name) }) {
                    departmentId.value = it
                }
            }

        }
        item {
            Row (modifier = Modifier.apply {
                fillMaxWidth().padding(20.dp)
            }, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = {
                    navHostController.navigate("department-list")
                }) {
                    Text(text = "Huỷ bỏ")
                }
                Button(onClick = {


                }) {
                    Text(text = "Create")
                }
            }
        }


    }
}


@Composable
fun ImageFromUri(contentResolver: ContentResolver, uri: String) {
    val bitmap: Bitmap? = loadBitmap(contentResolver, uri)
    bitmap?.asImageBitmap()?.let {
        Image(bitmap = it, contentDescription = "Image Description", modifier = Modifier
            .width(100.dp)
            .height(50.dp))
    }
}

fun loadBitmap(contentResolver: ContentResolver, uri: String): Bitmap? {
    var inputStream: InputStream? = null
    var bitmap: Bitmap? = null
    try {
        inputStream = contentResolver.openInputStream(Uri.parse(uri))
        bitmap = BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        inputStream?.close()
    }
    return bitmap
}
