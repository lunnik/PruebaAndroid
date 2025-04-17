package com.argaed.pruebaedgar.feature.domain.use_case.get_employee_details

import com.araged.clean.Either
import com.araged.clean.UseCase
import com.argaed.pruebaedgar.feature.domain.EmployeeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetEmployeeDetailsUseCase @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : UseCase<GetEmployeeDetailsResponse, GetEmployeeDetailsParams, GetEmployeeDetailsFailure>() {


    override suspend fun run(params: GetEmployeeDetailsParams):
            Either<GetEmployeeDetailsFailure, GetEmployeeDetailsResponse> =
        withContext(defaultDispatcher) {
            employeeRepository.getEmployeeDetails(params.id)
        }

}