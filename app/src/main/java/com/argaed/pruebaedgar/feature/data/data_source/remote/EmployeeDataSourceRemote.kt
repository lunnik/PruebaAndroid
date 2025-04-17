package com.argaed.pruebaedgar.feature.data.data_source.remote

import android.util.Log
import com.araged.clean.Either
import com.araged.failurehandler.message
import com.argaed.pruebaedgar.feature.data.EmployeeDataSource
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee.GetEmployeeFailure
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee.GetEmployeeResponse
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee_details.GetEmployeeDetailsFailure
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee_details.GetEmployeeDetailsResponse
import com.argaed.pruebaedgar.httpclient.retrofit.retrofitApiCall
import retrofit2.HttpException
import javax.inject.Inject

class EmployeeDataSourceRemote @Inject constructor(
    private val employeeApiServices: EmployeeApiServices,
) : EmployeeDataSource {

    override suspend fun getEmployee():
            Either<GetEmployeeFailure, GetEmployeeResponse> = try {
        retrofitApiCall {
            employeeApiServices.getEmployee()
        }.let {
            it.toEmployee()
            Either.Right(GetEmployeeResponse(it.toEmployee()))
        }
    } catch (exception: Exception) {
        val failure: GetEmployeeFailure = when (exception) {
            is HttpException -> exception.toGetEmployeeFailure()
            else -> GetEmployeeFailure.UnknownFailure(exception.message())
        }
        Either.Left(failure)
    }

    override suspend fun getEmployeeDetails(id :String): Either<GetEmployeeDetailsFailure, GetEmployeeDetailsResponse> =
        try {
            retrofitApiCall {
                employeeApiServices.getEmployeeDetails(id)
            }.let {
                Either.Right(GetEmployeeDetailsResponse(it.data.toEmployeeDetails()))
            }
        } catch (exception: Exception) {
            Log.e("eeeeee", exception.message())
            val failure: GetEmployeeDetailsFailure = when (exception) {
                is HttpException -> exception.toGetEmployeeDetailsFailure()
                else -> GetEmployeeDetailsFailure.UnknownFailure(exception.message())
            }
            Either.Left(failure)
        }
}