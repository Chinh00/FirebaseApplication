package com.supperman.firebaseapplication.application.modules.department._list

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
class DepartmentListViewModel @Inject constructor() : ViewModel () {
    val _list = MutableStateFlow(emptyList<Pair<String, Department>>())
    val list = _list.asStateFlow()
    val repositoryImpl = DepartmentRepositoryImpl()

    init {
        HandleGetList()
    }
    fun HandleGetList () {
        viewModelScope.launch {
            val result = repositoryImpl.GetList()
            _list.value = result
        }
    }

}