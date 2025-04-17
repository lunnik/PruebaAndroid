package com.argaed.pruebaedgar.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.araged.clean.onLeft
import com.araged.clean.onRight
import com.argaed.pruebaedgar.clean.core.presentation.Status
import com.argaed.pruebaedgar.clean.core.Failure
import com.argaed.pruebaedgar.di.components.IoDispatcher
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee.GetEmployeeParams
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee.GetEmployeeUseCase
import com.argaed.pruebaedgar.model.Employee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject



typealias EmployeeStatus = Status<Failure, Employee>


data class EmployeeState(
    val employee: Employee = Employee(),
    val employeeStatus: EmployeeStatus = Status.Loading(),
)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getEmployeeUseCase: GetEmployeeUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
): ViewModel () {
    private val _uiState = MutableStateFlow(EmployeeState())
    val uiState: Flow<EmployeeState> = _uiState

    fun loadEmployee() = viewModelScope.launch {
        withContext(ioDispatcher) {
            getEmployeeUseCase.run(params = GetEmployeeParams)
                .onLeft { failure -> showError(failure) }
                .onRight { response ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            employee = response.employee,
                            employeeStatus = Status.Done(response.employee)
                        )
                    }
                }
        }

    }

    private fun showError(failure: Failure) {
        _uiState.update { currentState ->
            currentState.copy(
                employeeStatus = Status.Failed(failure)
            )
        }
    }

}