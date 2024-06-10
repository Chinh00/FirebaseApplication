package com.supperman.firebaseapplication.application.modules.department

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.supperman.firebaseapplication.core.repository.Repository
import com.supperman.firebaseapplication.domain.entities.Department
import kotlinx.coroutines.tasks.await

class DepartmentRepositoryImpl : Repository<Department> {
    private val firebaseStore = Firebase.database
    override suspend fun FindByIdAsync(id: String): Pair<String, Department> {
        val entity_snapshot = firebaseStore.reference.child("Departments").child(id).get().await()
        val tmp = entity_snapshot.getValue(Department::class.java)
        if (tmp != null) {
            Log.d("Chinhee", tmp.name.toString())
            return entity_snapshot.key.toString() to tmp
        }
        throw Exception("Not found exception")
    }

    override suspend fun RemoveAsync(id: String): Department {
        TODO("Not yet implemented")
    }

    override suspend fun GetList(): List<Pair<String, Department>> {
        val dataSnapshot = firebaseStore.reference.child("Departments").get().await()
        val resultList = mutableListOf<Pair<String, Department>>()
        for (snapshot in dataSnapshot.children) {
            if (snapshot.exists()) {
                resultList.add(snapshot.key.toString() to snapshot.getValue(Department::class.java)!!)
            }
        }
        return resultList
    }

    override suspend fun UpdateAsync(entity: Department): Department {
        TODO("Not yet implemented")
    }

    override suspend fun AddAsync(entity: Department): Department? {
        val studentId = firebaseStore.reference.child("Departments").push().key
        studentId?.let { firebaseStore.reference.child("Departments").child(it).setValue(entity).await() }
        return studentId?.let { FindByIdAsync(it).second }
    }
}