package com.supperman.firebaseapplication.application.modules.contract.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.supperman.firebaseapplication.application.components.BaseScreen
import com.supperman.firebaseapplication.R
import com.supperman.firebaseapplication.application.modules.department.DepartmentMainActivity
import com.supperman.firebaseapplication.application.modules.employee.EmployeeMainActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            MainScreen()
        }
    }
}

@Composable
@Preview
fun MainScreen () {
    val context = LocalContext.current
    val mainNavigateConfig = listOf<MainNavigateConfig>(
        MainNavigateConfig(
            onClick = {
                val intent = Intent(context, DepartmentMainActivity::class.java)
                context.startActivity(intent)
            },
            icon = R.drawable.department,
            title = "Phòng ban"
        ),
        MainNavigateConfig(
            onClick = {
                val intent = Intent(context, EmployeeMainActivity::class.java)
                context.startActivity(intent)
            },
            icon = R.drawable.employee,
            title = "Nhân sự"
        )
    )




    BaseScreen(content = {
        LazyColumn (modifier = Modifier
            .padding(40.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            itemsIndexed(mainNavigateConfig.chunked(2)) {
                index, i -> Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                i.forEach { item ->
                    BoxItemClick(config = item)
                }
            }
            }
        }
    })
}


data class MainNavigateConfig (
    val onClick: () -> Unit,
    val title: String,
    val icon: Int
)






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxItemClick (config: MainNavigateConfig) {
    OutlinedCard (modifier = Modifier.apply {
        width(100.dp)
            .height(100.dp)
            .padding(20.dp)
            .border(
                BorderStroke(10.dp, Color.Red),
                MaterialTheme.shapes.large
            )
            .background(Color.Blue)
    },
        colors = CardDefaults.cardColors(
        containerColor = Color.Gray,
        contentColor = Color.White),
        onClick = {config.onClick()}
    ){
        Column (modifier = Modifier.apply {
            fillMaxSize()
        }, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(config.icon), modifier = Modifier.scale(0.9f), contentDescription = "")
            Text(text = config.title)
        }
    }
}

