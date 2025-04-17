package com.argaed.pruebaedgar.feature.data.data_source.remote

import com.argaed.pruebaedgar.feature.domain.use_case.get_employee.GetEmployeeFailure
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee_details.GetEmployeeDetailsFailure
import com.argaed.pruebaedgar.httpclient.retrofit.HttpErrorCode
import com.argaed.pruebaedgar.httpclient.retrofit.errorMessage
import retrofit2.HttpException


internal fun HttpException.toGetEmployeeFailure(): GetEmployeeFailure =
    when (HttpErrorCode.fromCode(code())) {
        HttpErrorCode.BAD_REQUEST -> GetEmployeeFailure.UserDisabled
        else -> GetEmployeeFailure.ServerFailure(code(), errorMessage())
    }

internal fun HttpException.toGetEmployeeDetailsFailure(): GetEmployeeDetailsFailure =
    when (HttpErrorCode.fromCode(code())) {
        HttpErrorCode.BAD_REQUEST -> GetEmployeeDetailsFailure.UserDisabled
        else -> GetEmployeeDetailsFailure.ServerFailure(code(), errorMessage())
    }





