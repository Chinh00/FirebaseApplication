package com.supperman.firebaseapplication.application.modules.employee

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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




