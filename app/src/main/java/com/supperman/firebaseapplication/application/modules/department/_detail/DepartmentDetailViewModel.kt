package com.supperman.firebaseapplication.application.modules.department._detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supperman.firebaseapplication.application.modules.department.DepartmentRepositoryImpl
import com.supperman.firebaseapplication.domain.entities.Department
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentDetailViewModel @Inject constructor() : ViewModel() {
    val _department = MutableStateFlow<Pair<String, Department>>(Pair("", Department()))
    val department = _department.asStateFlow()
    val departmentRepositoryImpl = DepartmentRepositoryImpl()
    fun HandleGetDepartmentDetail (id: String) {
        viewModelScope.launch {
            _department.value = departmentRepositoryImpl.FindByIdAsync(id)
        }
    }

}