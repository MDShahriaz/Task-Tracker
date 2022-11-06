import com.example.login.taskcontrollernetwork.getmodel.GetModel
import com.example.login.taskcontrollernetwork.taskdetailsmodel.TaskDetailResponseModel
import com.example.login.taskcontrollernetwork.taskpostmodel.TaskPostModel
import com.example.login.taskcontrollernetwork.taskpostmodel.TaskPostResponseModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskApIInterface {

    @GET("/task/details/{id}")
    suspend fun getTaskDetail(@Path("id") number:Int):TaskDetailResponseModel
}



