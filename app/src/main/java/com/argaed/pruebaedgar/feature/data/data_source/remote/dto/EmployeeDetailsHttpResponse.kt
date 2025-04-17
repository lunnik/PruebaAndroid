package com.argaed.pruebaedgar.feature.data.data_source.remote.dto

import com.argaed.pruebaedgar.model.EmployeeDetails
import com.google.gson.annotations.SerializedName

data class EmployeeDetailsHttpResponse(

    @SerializedName("data")
    val data: EmployeeDetailsDto,

    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: String,
)

data class EmployeeDetailsDto(

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
    fun toEmployeeDetails() = EmployeeDetails(
        profileImage = profileImage,
        employeeName = employeeName,
        employeeSalary = employeeSalary,
        id = id,
        employeeAge = employeeAge
    )
}
