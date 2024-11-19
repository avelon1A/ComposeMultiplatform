package org.aman.coding.data.remote

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.aman.coding.domain.model.appModel.DataList
import org.aman.coding.domain.model.request.LoginRequest
import org.aman.coding.domain.model.request.LoginResponse
import org.aman.coding.domain.model.respnonse.Problem
import org.aman.coding.domain.model.respnonse.ProblemDetails
import org.aman.coding.uitl.NetworkError
import org.aman.coding.uitl.Result


class ApiClient(
    private val httpClient: HttpClient
) {


    suspend fun login(username: String, password: String): Result<LoginResponse, NetworkError> {
        val response = try {
            httpClient.post(
                urlString = "https://dummyjson.com/auth/login"
            ) {
                contentType(ContentType.Application.Json)
                setBody(LoginRequest(username = username, password = password))
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val loginResponse = response.body<LoginResponse>()
                Result.Success(loginResponse)
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }


    suspend fun getDataList(): DataList {
        val response = httpClient.get("https://testcoverage-latest.onrender.com/categories") {
            header(HttpHeaders.ContentType, ContentType.Application.Any)
        }
           return Json.decodeFromString(response.body())
    }

    suspend fun getProblemsByCategory(category: String): Result<List<Problem>, NetworkError> {
        val response = try {
            httpClient.get("https://testcoverage-latest.onrender.com/problems") {
                parameter("category", category)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        } catch (e: TimeoutCancellationException) {
            return Result.Error(NetworkError.REQUEST_TIMEOUT)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val problems = response.body<List<Problem>>()
                Result.Success(problems)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    suspend fun compileCode(code:String): Result<String, NetworkError> {
        val response =  try {
            httpClient.post("https://testcoverage-latest.onrender.com/runjava") {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                setBody(code)
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        } catch (e: TimeoutCancellationException) {
            return Result.Error(NetworkError.REQUEST_TIMEOUT)
        }


        return when (response.status.value) {
            in 200..299 -> {
                val problems = response.body<String>()
                Logger.d("java compile $problems")
                Result.Success(problems)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }

    }

    suspend fun getDetailedProblem(id:Int,category: String): Result<ProblemDetails, NetworkError> {
        val response =  try {
            httpClient.get("https://testcoverage-latest.onrender.com/problem/$id") {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                parameter("category", category)

            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        } catch (e: TimeoutCancellationException) {
            return Result.Error(NetworkError.REQUEST_TIMEOUT)
        }
        Logger.d("getDetailedProblem $response")
        return when (response.status.value) {
            in 200..299 -> {
                val problems = response.body<ProblemDetails>()
                Result.Success(problems)

            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }


}

