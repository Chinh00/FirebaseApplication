package com.supperman.firebaseapplication.application.modules.department._create

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import com.supperman.firebaseapplication.application.modules.department.DepartmentRepositoryImpl
import com.supperman.firebaseapplication.domain.entities.Department
import com.supperman.firebaseapplication.infrastructure.utils.state.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class DepartmentCreateViewModel @Inject constructor() : ViewModel() {
    val storage = Firebase.storage.reference
    val _apiState = MutableStateFlow(ApiState.NONE)
    val apiState = _apiState.asStateFlow()
    val repository = DepartmentRepositoryImpl()
    @RequiresApi(Build.VERSION_CODES.P)
    fun HandleCreateDepartment (
        department: DepartmentCreateModel
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _apiState.emit(ApiState.LOADING)

                val uploadTask = storage.child("${LocalDateTime.now()}").putFile(department.logo)

                uploadTask.addOnCompleteListener {task -> if (task.isSuccessful) {
                    viewModelScope.launch {
                        repository.AddAsync(
                            Department(
                                name = department.name,
                                email = department.email,
                                website = department.website,
                                logo = task.result.storage.downloadUrl.await().toString(),
                                address = department.address,
                                phone = department.phone,
                                departmentId = department.departmentId
                            )
                        )
                        _apiState.emit(ApiState.SUCCESS)
                    }
                } }

            } catch (e: Exception) {
                e.printStackTrace()
                _apiState.emit(ApiState.ERROR)
            } finally {
                _apiState.emit(ApiState.NONE)
            }
        }
    }
}