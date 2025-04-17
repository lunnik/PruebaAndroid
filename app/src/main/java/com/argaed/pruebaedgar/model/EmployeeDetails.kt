package com.argaed.pruebaedgar.model


data class EmployeeDetails(
    val profileImage: String,

    val employeeName: String,

    val employeeSalary: Int,

    val id: Int,

    val employeeAge: Int,
) {

    constructor() : this(
        profileImage = "", employeeName = "Edgar Arana", employeeSalary = 10000, id = 5, employeeAge = 50
    )
}