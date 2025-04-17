package com.argaed.pruebaedgar.model


data class EmployeeDetails(
    val profileImage: String,

    val employeeName: String,

    val employeeSalary: Int,

    val id: Int,

    val employeeAge: Int,
) {

    constructor() : this(
        profileImage = "", employeeName = "", employeeSalary = 0, id = 0, employeeAge = 0
    )
}