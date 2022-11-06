package Network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegistrationRequestModel(
    val email: String,
    val otp: Int,
    val password: String,
    val sessionId: String
):Parcelable