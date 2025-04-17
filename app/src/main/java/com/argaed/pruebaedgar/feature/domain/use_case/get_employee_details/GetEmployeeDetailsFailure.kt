package com.argaed.pruebaedgar.feature.domain.use_case.get_employee_details

import com.araged.failurehandler.HttpFailure
import com.araged.failurehandler.NetworkFailure
import com.argaed.pruebaedgar.clean.core.Failure

sealed class GetEmployeeDetailsFailure : Failure() {

    object NetworkConnectionFailure : GetEmployeeDetailsFailure(), NetworkFailure


    object UserDisabled : GetEmployeeDetailsFailure()


    object UserTimeOut : GetEmployeeDetailsFailure()


    data class ServerFailure(
        override val code: Int,
        override val message: String,
    ) : GetEmployeeDetailsFailure(), HttpFailure


    data class UnknownFailure(
        val message: String,
    ) : GetEmployeeDetailsFailure()
}