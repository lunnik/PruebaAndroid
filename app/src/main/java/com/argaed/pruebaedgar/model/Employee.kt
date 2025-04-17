package com.argaed.pruebaedgar.model


data class Employee(
    val data: List<EmployeeData> = listOf(),

    val message: String = "",

    val status: String = "",
) {

    constructor() : this(
        listOf(), "", ""
    )
}

data class EmployeeData(
    val profileImage: String,

    val employeeName: String,

    val employeeSalary: Int,

    val id: Int,

    val employeeAge: Int,
) {
    constructor() : this("profileImage", "employeeName", 0, 0, 0)
}