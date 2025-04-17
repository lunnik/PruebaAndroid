package com.argaed.pruebaedgar.feature.data.data_source.remote

import com.argaed.pruebaedgar.feature.data.data_source.remote.dto.EmployeeDetailsHttpResponse
import com.argaed.pruebaedgar.feature.data.data_source.remote.dto.EmployeeHttpResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EmployeeApiServices {


    @GET(URL.GET_EMPLOYEE)
    suspend fun getEmployee(): Response<EmployeeHttpResponse>

    @GET(URL.GET_EMPLOYEE_DETAILS)
    suspend fun getEmployeeDetails(
        @Path("employee_id") movieId: String,
    ): Response<EmployeeDetailsHttpResponse>


    private object URL {
        const val GET_EMPLOYEE: String = "/api/v1/employees"
        const val GET_EMPLOYEE_DETAILS: String = "/api/v1/employee/{employee_id}"

    }
}