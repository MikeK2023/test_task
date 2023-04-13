package com.example.testcoherent.http.common

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data

@Data
open class ApiResponse<T> @JsonCreator protected constructor(
    @param:JsonProperty("status") val status: ResponseStatus,
    @param:JsonProperty("payload") val payload: T,
    @param:JsonProperty("message") val message: String?
) {
    companion object {

        fun <T> ok(payload: T): ApiResponse<T> {
            return ApiResponse(ResponseStatus.OK, payload, null)
        }

        fun <T> error(message: String?): ApiResponse<T?>? {
            return error(null, message)
        }

        fun <T> error(payload: T, message: String?): ApiResponse<T>? {
            return ApiResponse<T>(ResponseStatus.ERROR, payload, message)
        }
    }
}


