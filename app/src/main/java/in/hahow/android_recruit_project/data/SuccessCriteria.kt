package `in`.hahow.android_recruit_project.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SuccessCriteria(
    @Json(name = "numSoldTickets") val numSoldTickets: Int
)