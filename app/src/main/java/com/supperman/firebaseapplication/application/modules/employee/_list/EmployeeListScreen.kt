package com.supperman.firebaseapplication.application.modules.employee._list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.supperman.firebaseapplication.domain.entities.Department


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeListScreen (
    navController: NavHostController
) {
    val key_search = remember {
        mutableStateOf("")
    }
    val departmentListViewModel: DepartmentListViewModel = hiltViewModel()
    /*LaunchedEffect(Unit) {
        departmentListViewModel.HandleGetList()
    }*/
    departmentListViewModel.HandleGetList()
    val list = departmentListViewModel.list.collectAsState()
    Scaffold (
        topBar = {
                 CenterAlignedTopAppBar(title = { Text(text = "Danh sách phòng ban") })
        },
        floatingActionButton = {
        Button(onClick = {
            navController.navigate("department-add")
        }) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }) {
        val res = list.value.sortedBy { it.second.name }.groupBy { it.second.name.first() }
        Column(modifier = Modifier.padding(it)) {
            TextField(value = key_search.value, onValueChange = {key_search.value = it}, modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth())
            LazyColumn (modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {
                 itemsIndexed(res.toList()) {
                     index, it -> DepartmentComp(it.first.toString(), it.second, navController)
                 }
            }

        }
    }
}

@Composable
fun DepartmentComp (key: String, list: List<Pair<String, Department>>, navController: NavHostController) {
    Text(text = key, modifier = Modifier.fillMaxWidth().padding(5.dp), fontSize = 17.sp, fontWeight = FontWeight.Bold)
    list.forEach { department -> DepartmentItem(item = department, navController) }
}

@Composable
fun DepartmentItem (item: Pair<String, Department>, navController: NavHostController) {
    Row (modifier = Modifier
        .fillMaxWidth().clickable { navController.navigate("department/detail/${item.first}") }
        .clip(RoundedCornerShape(10.dp))
        .background(Color.LightGray)
        .padding(15.dp), Arrangement.Start, Alignment.CenterVertically) {
        AsyncImage(model = item.second.logo, contentDescription = "", modifier = Modifier.padding(10.dp))
        Text(text = item.second.name, modifier = Modifier.apply { padding(5.dp) }, fontSize = 17.sp)
    }
    Spacer(modifier = Modifier
        .height(10.dp)
        .fillMaxWidth())
}


