package Network

import Network.loginModel.LoginRequestModel
import Network.loginModel.LoginResponseModel
import Network.otpmodel.DataOTP
import Network.otpmodel.OtpRequestModel
import Network.otpmodel.OtpResponseModel
import com.example.login.taskcontrollernetwork.TaskTrackerBaseResponse
import com.example.login.taskcontrollernetwork.getmodel.GetModel
import com.example.login.taskcontrollernetwork.taskdetailsmodel.TaskDetailResponseModel
import com.example.login.taskcontrollernetwork.taskpostmodel.TaskPostModel
import com.example.login.taskcontrollernetwork.taskpostmodel.TaskPostResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import io.reactivex.Observable


interface ApIInterface {
    @POST("/generateOtp")
    suspend fun setGenerateOTP(@Body otpRequestModel: OtpRequestModel):Response<TaskTrackerBaseResponse<DataOTP>>

    @POST("/register")
    suspend fun sentRegisterInfo(@Body registrationRequestModel: RegistrationRequestModel):Response<RegistrationResponseModel>

    @POST("/login")
   fun sentLoginInfo(@Body loginRequestModel: LoginRequestModel):Observable<LoginResponseModel>

    @POST("/task")
    suspend fun postTask(@Body taskPostModel: TaskPostModel):TaskPostResponseModel

    @GET("/task")
    fun getTask():Observable<GetModel>

    @GET("/task/details/{id}")
    suspend fun getTaskDetail(@Path("id") number:Int):TaskDetailResponseModel

}
