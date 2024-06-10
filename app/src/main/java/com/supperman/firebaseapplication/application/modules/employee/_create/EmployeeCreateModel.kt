package com.supperman.firebaseapplication.application.modules.employee._create

import android.net.Uri

data class DepartmentCreateModel (
    var name: String,
    var email: String,
    var website: String,
    var logo: Uri,
    var address: String,
    var phone: String,
    var departmentId: String
)
data class DepartmentReadModel (
    val id: String,
    var name: String,
    var email: String,
    var website: String,
    var logo: Uri,
    var address: String,
    var phone: String,
    var departmentId: String
)