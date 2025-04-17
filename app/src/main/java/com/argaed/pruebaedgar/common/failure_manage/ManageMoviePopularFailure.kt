package com.araged.jetpack.presentation.home.failure_manage

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.araged.failurehandler.DefaultFailure
import com.araged.failurehandler.HttpFailure
import com.araged.failurehandler.NetworkFailure
import com.argaed.pruebaedgar.R
import com.argaed.pruebaedgar.clean.core.Failure
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee.GetEmployeeFailure

fun Context.manageEmployeeFailure(
    failure: GetEmployeeFailure,
){
    getCommonFailureMessage(this, failure)
}

fun getCommonFailureMessage(
    context: Context,
    failure: GetEmployeeFailure
) {
    val message: String = context.getCommonFailureMessage(failure)
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

/**
 *
 */
fun Context.getNetworkConnectionFailureMessage() =
    getString(R.string.failure_network_connection)

/**
 *
 */
fun Context.getServerFailure(httpFailure: HttpFailure) =
    getString(R.string.failure_http_code, httpFailure.code, httpFailure.message)

/**
 *
 */
fun Context.getHttpCodeFailureMessage(code: Int, message: String) =
    getString(R.string.failure_http_code, code, message)

/**
 *
 */
fun Context.getUnknownFailureMessage(message: String) =
    getString(R.string.failure_unknown, message)

/**
 *
 */
fun Fragment.getNetworkConnectionFailureMessage() =
    requireContext().getNetworkConnectionFailureMessage()


/**
 *
 */
fun Fragment.getUnknownFailureMessage(message: String) =
    requireContext().getUnknownFailureMessage(message)

/**
 *
 */
fun Fragment.getCommonFailureMessage(failure: Failure): String =
    requireContext().getCommonFailureMessage(failure)

/**
 *
 */
fun Context.getCommonFailureMessage(failure: Failure): String =
    when (failure) {
        is NetworkFailure -> getNetworkConnectionFailureMessage()
        is HttpFailure -> getServerFailure(failure)
        is DefaultFailure -> getUnknownFailureMessage(failure.message)
        else -> getUnknownFailureMessage(failure.javaClass.simpleName)
    }

/**
 *
 */
fun Context.manageCommonFailureByToast(failure: Failure) {
    val message: String = getCommonFailureMessage(failure)
    showLongToast(message)
}

fun <T> Context.showLongToast(value: T) =
    Toast.makeText(this, value.toString(), Toast.LENGTH_LONG).show()

/* */
fun Fragment.manageCommonFailureByToast(failure: Failure) =
    requireContext().manageCommonFailureByToast(failure)