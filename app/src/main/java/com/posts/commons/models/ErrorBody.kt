package com.posts.commons.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorBody(
    @SerializedName("code")
    @Expose
    var code: Int? = null,
    @SerializedName("details")
    @Expose
    var details: String? = "",
    @SerializedName("message")
    @Expose
    var title: String? = "",
)