package com.ibm.hilt.repository

import com.ibm.hilt.util.Resource

abstract class BaseRepository : AppRepository {
    companion object{
        const val ERROR_CODE = "401"
        const val UNAUTHORIZED = "Unauthorized"
        const val NOT_FOUND = "Not found"
        const val SOMETHING_WRONG = "Something went wrong"

        fun <T : Any> handleSuccess(data: T): Resource<T> {
            return Resource.Success(data)
        }

        fun <T : Any> handleException(code: Int): Resource<T> {
            val exception = getErrorMessage(code)
            return Resource.Error(null, Exception(exception).toString())
        }

        private fun getErrorMessage(httpCode: Int): String {
            return when (httpCode) {
                401 -> UNAUTHORIZED
                404 -> NOT_FOUND
                else -> SOMETHING_WRONG
            }
        }
    }
}