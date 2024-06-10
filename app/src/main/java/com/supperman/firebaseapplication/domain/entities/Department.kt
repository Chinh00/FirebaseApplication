package com.supperman.firebaseapplication.domain.entities

import com.supperman.firebaseapplication.core.domain.BaseEntity

class Department {
    var name: String = ""
    var email: String = ""
    var website: String = ""
    var logo: String = ""
    var address: String = ""
    var phone: String = ""
    var departmentId: String = ""

    constructor() // Default constructor required for Firebase serialization/deserialization

    constructor(
        name: String,
        email: String,
        website: String,
        logo: String,
        address: String,
        phone: String,
        departmentId: String
    ) {
        this.name = name
        this.email = email
        this.website = website
        this.logo = logo
        this.address = address
        this.phone = phone
        this.departmentId = departmentId
    }
}