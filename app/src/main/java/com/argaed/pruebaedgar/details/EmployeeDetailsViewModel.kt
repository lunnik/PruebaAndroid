package com.argaed.pruebaedgar.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.araged.clean.onLeft
import com.araged.clean.onRight
import com.argaed.pruebaedgar.clean.core.Failure
import com.argaed.pruebaedgar.clean.core.presentation.Status
import com.argaed.pruebaedgar.di.components.IoDispatcher
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee_details.GetEmployeeDetailsParams
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee_details.GetEmployeeDetailsUseCase
import com.argaed.pruebaedgar.model.EmployeeDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


typealias EmployeeDetailsStatus = Status<Failure, EmployeeDetails>


data class EmployeeDetailsState(
    val employeeDetails: EmployeeDetails = EmployeeDetails(),
    val employeeDetailsStatus: EmployeeDetailsStatus = Status.Loading(),
)

@HiltViewModel
class EmployeeDetailsViewModel @Inject constructor(
    private val getEmployeeDetailsUseCase: GetEmployeeDetailsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel()  {
    private val _uiState = MutableStateFlow(EmployeeDetailsState())
    val uiState: Flow<EmployeeDetailsState> = _uiState


    fun loadEmployeeDetails(id: String) = viewModelScope.launch {
        withContext(ioDispatcher) {
            getEmployeeDetailsUseCase.run(params = GetEmployeeDetailsParams(id))
                .onLeft { failure -> showError(failure) }
                .onRight { response ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            employeeDetails = response.employeeDetails,
                            employeeDetailsStatus = Status.Done(response.employeeDetails)
                        )
                    }
                }
        }

    }

    private fun showError(failure: Failure) {
        _uiState.update { currentState ->
            currentState.copy(
                employeeDetailsStatus = Status.Failed(failure)
            )
        }
    }


}