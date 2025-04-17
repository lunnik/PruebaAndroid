package com.argaed.pruebaedgar.feature.data

import com.araged.clean.Either
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee.GetEmployeeFailure
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee.GetEmployeeResponse
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee_details.GetEmployeeDetailsFailure
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee_details.GetEmployeeDetailsResponse

interface EmployeeDataSource {

    suspend fun getEmployee(
    ): Either<GetEmployeeFailure, GetEmployeeResponse>

    suspend fun getEmployeeDetails(
        idS:String
    ): Either<GetEmployeeDetailsFailure, GetEmployeeDetailsResponse>

}