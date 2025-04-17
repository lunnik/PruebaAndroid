package com.argaed.pruebaedgar.feature.domain.use_case.get_employee

import com.araged.clean.Either
import com.araged.clean.UseCase
import com.argaed.pruebaedgar.feature.domain.EmployeeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetEmployeeUseCase @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : UseCase<GetEmployeeResponse, GetEmployeeParams, GetEmployeeFailure>() {


    override suspend fun run(params: GetEmployeeParams):
            Either<GetEmployeeFailure, GetEmployeeResponse> =
        withContext(defaultDispatcher) {
            employeeRepository.getEmployee()
        }

}