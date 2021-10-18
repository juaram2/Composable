package com.example.composable.service

import com.example.composable.model.account.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface AccountsApi {
    @POST("api/v1/accounts/changeEmail")
    suspend fun apiV1AccountsChangeEmailPost(@Body changeEmailModel: ChangeEmailModel? = null): Response<Unit>

    @POST("api/v1/accounts/changePassword")
    suspend fun apiV1AccountsChangePasswordPost(@Body setPasswordRequestModel: SetPasswordRequestModel? = null): Response<Unit>

    @POST("api/v1/accounts/confirmEmail")
    suspend fun apiV1AccountsConfirmEmailPost(@Body confirmEmailModel: ConfirmEmailModel? = null): Response<Unit>

    @DELETE("api/v1/accounts")
    suspend fun apiV1AccountsDelete(): Response<Unit>

    @POST("api/v1/accounts/forgotPassword")
    suspend fun apiV1AccountsForgotPasswordPost(@Body forgotPasswordViewModel: ForgotPasswordViewModel? = null): Response<Unit>

    @GET("api/v1/accounts")
    suspend fun apiV1AccountsGet(): Response<Account>

    @POST("api/v1/accounts")
    suspend fun apiV1AccountsPost(@Body registerViewModel: RegisterViewModel? = null): Response<Unit>

    @POST("api/v1/accounts/resetPassword")
    suspend fun apiV1AccountsResetPasswordPost(@Body resetPasswordViewModel: ResetPasswordViewModel? = null): Response<Unit>

    @POST("api/v1/accounts/sendVerificationEmail")
    suspend fun apiV1AccountsSendVerificationEmailPost(): Response<Unit>

}