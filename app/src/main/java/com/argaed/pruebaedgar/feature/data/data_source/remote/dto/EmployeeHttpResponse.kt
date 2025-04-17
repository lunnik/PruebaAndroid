package com.argaed.pruebaedgar.feature.data.data_source.remote.dto

import com.argaed.pruebaedgar.model.Employee
import com.argaed.pruebaedgar.model.EmployeeData
import com.google.gson.annotations.SerializedName

data class EmployeeHttpResponse(

    @SerializedName("data")
    val data: List<EmployeeDataDto>,

    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: String,
) {

    fun toEmployee() =
        Employee(data = data.map { it.toEmployeeData() }, message = message, status = status)
}

data class EmployeeDataDto(

    @SerializedName("profile_image")
    val profileImage: String,

    @SerializedName("employee_name")
    val employeeName: String,

    @SerializedName("employee_salary")
    val employeeSalary: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("employee_age")
    val employeeAge: Int,
) {
    fun toEmployeeData() = EmployeeData(
        profileImage = profileImage,
        employeeName = employeeName,
        employeeSalary = employeeSalary,
        id = id,
        employeeAge = employeeAge
    )
}
