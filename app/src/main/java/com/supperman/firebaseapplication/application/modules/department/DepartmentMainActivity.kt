package com.supperman.firebaseapplication.application.modules.department

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.supperman.firebaseapplication.application.modules.department._create.DepartmentCreateScreen
import com.supperman.firebaseapplication.application.modules.department._detail.DepartmentDetailScreen
import com.supperman.firebaseapplication.application.modules.department._list.DepartmentListScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DepartmentMainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DepartmentMainScreen()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DepartmentMainScreen (
    //backPress: () -> Unit
){
    val navController = rememberNavController()
    Scaffold (

    ) {
        NavHost(navController = navController, startDestination = "department-list" , modifier = Modifier.padding(it)) {
            composable("department-list") { DepartmentListScreen(navController) }
            composable("department-add") { DepartmentCreateScreen(navController) }
            composable("department/detail/{id}") { DepartmentDetailScreen(navController) }
        }
    }
}




