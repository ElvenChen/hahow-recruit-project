package `in`.hahow.android_recruit_project.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseData(
    @Json(name = "successCriteria") val successCriteria: SuccessCriteria,
    @Json(name = "numSoldTickets") val numSoldTickets: Int,
    @Json(name = "status") val status: String,
    @Json(name = "proposalDueTime") val proposalDueTime: String?,
    @Json(name = "coverImageUrl") val coverImageUrl: String,
    @Json(name = "title") val title: String,
    @Json(name = "totalVideoLengthInSeconds") val totalVideoLengthInSeconds: Int? = null
)