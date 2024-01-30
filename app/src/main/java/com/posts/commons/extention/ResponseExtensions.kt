package com.posts.commons.extention

import android.os.RemoteException
import android.util.AndroidException
import android.util.Log
import com.posts.commons.models.ResponseErrorResult
import com.google.gson.Gson
import com.posts.commons.models.ErrorBody
import com.posts.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.transform
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.net.*

val TAG = "ResponseExtensions"
inline fun <T> Flow<Response<T>>.transformResponseData(
    crossinline onSuccess: suspend FlowCollector<T>.(T) -> Unit,
): Flow<T> {
    return transform {
        val errorBody = it.errorBody()
        val body = it.body()


        if (it.isSuccessful && body != null) {
            body?.let { data ->
                onSuccess(data)
            }
            return@transform
        }
        errorBody?.let { errorBody ->
            when (it.code()) {
                Constants.HttpRequestErrorCode.SERVER_ERROR -> {
                    throw Throwable(Gson().toJson(errorBody.convertToResponseErrorResult(Constants.HttpRequestErrorCode.SERVER_ERROR)))
                }

                Constants.HttpRequestErrorCode.UN_AUTHORIZED -> {
                    throw Throwable(Gson().toJson(errorBody.convertToResponseErrorResult(Constants.HttpRequestErrorCode.UN_AUTHORIZED)))
                }

                Constants.HttpRequestErrorCode.PERMISSION_DENIED -> {
                    throw Throwable(Gson().toJson(errorBody.convertToResponseErrorResult(Constants.HttpRequestErrorCode.PERMISSION_DENIED)))
                }

                Constants.HttpRequestErrorCode.NOT_FOUND -> {
                    throw Throwable(Gson().toJson(
                        ResponseErrorResult(
                        Constants.HttpRequestErrorCode.NOT_FOUND,
                        ErrorBody(
                            Constants.HttpRequestErrorCode.CONNECTION_ERROR,
                            "ApiError", "Api not found"
                        )
                    )
                    ))
                }

                Constants.HttpRequestErrorCode.INVALID_INPUT -> {
                    throw Throwable(Gson().toJson(errorBody.convertToResponseErrorResult(Constants.HttpRequestErrorCode.INVALID_INPUT)))
                }

                else -> {
                    Log.e(TAG, it.code().toString() + "," + errorBody.toString())
                    throw Throwable().handleException()
                }
            }
        }
    }
}

fun ResponseBody?.convertToResponseErrorResult(code: Int): ResponseErrorResult {
    val jsonObject = JSONObject(this?.string())
    val errorObject: JSONObject = jsonObject.getJSONObject("error")
    Log.e(TAG, "errorObject: ${errorObject}")
    if (errorObject != null) {
        return ResponseErrorResult(
            code,
            Gson().fromJson(errorObject.toString(), ErrorBody::class.java)
        )
    } else
        return ResponseErrorResult(
            code,
            ErrorBody(
                Constants.HttpRequestErrorCode.CONNECTION_ERROR,
                "Error", "Error in api response!"
            )
        )
}

fun Throwable.handleException(): Throwable {
    Log.e("exception", this.javaClass.canonicalName + "," + this.javaClass.name)
    return if (this is AndroidException || this is RemoteException || this is BindException || this is PortUnreachableException || this is SocketTimeoutException || this is UnknownServiceException || this is UnknownHostException || this is IOException || this is ConnectException || this is NoRouteToHostException) {
        Throwable(
            Gson().toJson(
                ResponseErrorResult(
                    Constants.HttpRequestErrorCode.CONNECTION_ERROR,
                    ErrorBody(
                        Constants.HttpRequestErrorCode.CONNECTION_ERROR,
                        "Connection", "Test your connection"
                    )
                )
            )
        )
    } else {
        this
    }
}
