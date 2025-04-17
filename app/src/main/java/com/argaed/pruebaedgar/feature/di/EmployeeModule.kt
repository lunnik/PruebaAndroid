package com.argaed.pruebaedgar.feature.di

import com.argaed.pruebaedgar.feature.data.EmployeeRepositoryImpl
import com.argaed.pruebaedgar.feature.data.data_source.remote.EmployeeApiServices
import com.argaed.pruebaedgar.feature.data.data_source.remote.EmployeeDataSourceRemote
import com.argaed.pruebaedgar.feature.domain.EmployeeRepository
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee.GetEmployeeUseCase
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee_details.GetEmployeeDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class EmployeeModule {

    /** API SERVICE **/
    @Provides
    @Singleton
    fun provideEmployeeApiServices(
        retrofit: Retrofit,
    ): EmployeeApiServices = retrofit.create(EmployeeApiServices::class.java)

    @Provides
    @Singleton
    fun provideEmployeeDataSourceRemote(
        employeeApiServices: EmployeeApiServices,
    ): EmployeeDataSourceRemote = EmployeeDataSourceRemote(employeeApiServices)

    @Provides
    @Singleton
    fun provideMovieRepository(
        employeeDataSourceRemote: EmployeeDataSourceRemote
    ): EmployeeRepository = EmployeeRepositoryImpl(
        employeeDataSourceRemote = employeeDataSourceRemote
    )


    /** USE CASE*/
    @Provides
    fun provideGetEmployeeUseCase(
        employeeRepository: EmployeeRepository
    ) = GetEmployeeUseCase(employeeRepository)

    @Provides
    fun provideGetEmployeeDetailsUseCase(
        employeeRepository: EmployeeRepository
    ) = GetEmployeeDetailsUseCase(employeeRepository)
}