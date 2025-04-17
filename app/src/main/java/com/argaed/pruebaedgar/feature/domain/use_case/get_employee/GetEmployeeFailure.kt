package com.argaed.pruebaedgar.feature.domain.use_case.get_employee

import com.araged.failurehandler.HttpFailure
import com.araged.failurehandler.NetworkFailure
import com.argaed.pruebaedgar.clean.core.Failure

sealed class GetEmployeeFailure : Failure() {

    object NetworkConnectionFailure : GetEmployeeFailure(), NetworkFailure


    object UserDisabled : GetEmployeeFailure()


    object UserTimeOut : GetEmployeeFailure()


    data class ServerFailure(
        override val code: Int,
        override val message: String,
    ) : GetEmployeeFailure(), HttpFailure


    data class UnknownFailure(
        val message: String,
    ) : GetEmployeeFailure()
}