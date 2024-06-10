package com.supperman.firebaseapplication.core.repository

import com.supperman.firebaseapplication.core.domain.BaseEntity
import com.supperman.firebaseapplication.domain.entities.Department

interface Repository<T> {
    suspend fun FindByIdAsync(id: String): Pair<String, T>
    suspend fun AddAsync(entity: T): T?
    suspend fun UpdateAsync(entity: T): T
    suspend fun RemoveAsync(id: String): T
    suspend fun GetList(): List<Pair<String, T>>
}