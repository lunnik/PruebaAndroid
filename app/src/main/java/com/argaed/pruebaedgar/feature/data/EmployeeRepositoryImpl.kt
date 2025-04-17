package com.argaed.pruebaedgar.feature.data

import com.araged.clean.Either
import com.argaed.pruebaedgar.feature.data.data_source.remote.EmployeeDataSourceRemote
import com.argaed.pruebaedgar.feature.domain.EmployeeRepository
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee.GetEmployeeFailure
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee.GetEmployeeResponse
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee_details.GetEmployeeDetailsFailure
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee_details.GetEmployeeDetailsResponse
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val employeeDataSourceRemote: EmployeeDataSourceRemote,
) : EmployeeRepository {

    override suspend fun getEmployee(): Either<GetEmployeeFailure, GetEmployeeResponse> =
        employeeDataSourceRemote.getEmployee()

    override suspend fun getEmployeeDetails(id: String): Either<GetEmployeeDetailsFailure, GetEmployeeDetailsResponse> =
        employeeDataSourceRemote.getEmployeeDetails(id)


}