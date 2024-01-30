package com.posts.commons.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseErrorResult(
    @SerializedName("code")
    @Expose
    var code: Int? = null,
    @SerializedName("errorBody")
    @Expose
    var errorBody: ErrorBody? = null,
)