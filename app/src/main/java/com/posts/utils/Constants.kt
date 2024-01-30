package com.posts.utils

class Constants {
    object Public {
        var SERVER_BASE_URL = ""
        const val SPLASH_DISPLAY_LENGTH: Long = 2000
        const val REGISTRATION_PHONE_CODE = 20
        const val LOCATION_PERMISSION_REQUEST_CODE = 1022
        const val OPEN_LOCATION_SERVICE_REQUEST = 1021
        const val GALLERY_PERMISSION_REQUEST_CODE = 1024
        const val CAMERA_PERMISSION_REQUEST_CODE = 1032
    }

    object FileType {
        const val Video = 1
        const val Doc = 2
        const val Exel = 3
        const val PDF = 4
    }

    object HttpRequestErrorCode {
        const val UN_AUTHORIZED = 401

        //500
        const val SERVER_ERROR = 500
        const val INVALID_INPUT = 400

        //404
        const val NOT_FOUND = 404

        //403
        const val PERMISSION_DENIED = 403

        //no internet connection
        const val CONNECTION_ERROR = -1
    }

    object ErrorApi {
        //deleted or unauthorized 401
        const val UNAUTHORIZED = "Un Authorized"

        //500
        const val SERVER_ERROR = "Server Issue"

        //400
        const val BODY_ERROR = "Body Error"

        //400
        const val BAD_REQUEST = "Bad Request"
        const val INVALID_INPUT = "Invalid Input"

        //404
        const val NOT_FOUND = "Service Not Available"

        //403
        const val PERMISSION_DENIED = "permission Denied"

        //no internet connection
        const val CONNECTION_ERROR = "Internet connection issue"
    }

    data class CustomErrorThrow(val key: String, val value: String) : Throwable()

    object IntentActions{
        const val OPEN_MY_TRIP_DETAILS_ACTION = "com.upclicks.app.myTripDetails"
    }
    object Intent {
        const val AUTHENTICATION_TYPE_SELECTION = "authentication_type"
        const val ID = "id"
        const val OTP_TYPE = "otp_type"
        const val OTP_MESSAGE = "otp_message"
        const val TRIP_ID = "tripId"
        const val EMAIL_ADDRESS = "email_address"
        const val PICK_UP_LOCATION_ID = "pickup_location_id"
        const val PICK_UP_LOCATION_NAME = "pickup_location_name"
        const val DESTINATION_LOCATION_ID = "destination_location_id"
        const val DESTINATION_LOCATION_NAME = "destination_location_name"
        const val FORGOT_PASSWORD_RESPONSE = "ForgotPasswordResponse"
        const val LOCATIONS_LIST = "locations_list"
        const val ACCOUNT_IDENTITY = "accountIdentity"
        const val PASSWORD_RESET_CODE = "passwordResetCode"
    }

    object AboutAppKeys{
        const val ABOUT_US = "AboutUs"
        const val POLICY = "PolicyAndPrivacy"
    }

    object ApiMappingKey {
        const val SKIP = "skip"
        const val TAKE = "take"
        const val ID = "id"
        const val SHIPPING_REQUEST_TRIP_ID = "shippingRequestTripId"
        const val SHIPPING_REQUEST_ID = "shippingRequestId"
        const val SHIPPER_NAME = "shipperName"
        const val NAME = "name"
        const val SURNAME = "surname"
        const val PHONE_NUMBER = "phoneNumber"
        const val EMAIL_ADDRESS = "emailAddress"
        const val CODE = "code"
        const val ACCOUNT_IDENTITY = "accountIdentity"
        const val USERNAME_OR_EMAIL_ADDRESS = "userNameOrEmailAddress"
        const val PASSWORD = "password"
        const val REMEMBER_CLIENT = "rememberClient"
        const val PICK_UP_LOCATION_ID = "pickupLocationId"
        const val DESTINATION_LOCATION_ID = "destinationLocationId"
    }

    object DateFormats {
        const val PICK_UP_TIME = "d MMM HH:mm aa"
    }
    object FcmNotificationsTypes {
        const val OrderRequested = "10"
        const val OrderConfirmed = "20"
        const val OrderCancelled = "30"
        const val OrderRefused = "40"
        const val OrderFinished = "50"
        const val Order = "60"
        const val UPDATE_NOTIFICATION_COUNT = "300"
        const val Public_Notification = "500"
        const val LOGOUT_NOTIFY = "1000"
        const val CHAT_MSG_INCOME = "400"
    }

    object NavigationBottom {
        const val STATUS = 0
        const val DELIVERS = 1
        const val WALLET = 2
        const val MENU = 3
    }


}