package `in`.hahow.android_recruit_project.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Course(
    @Json(name = "data") val data: List<CourseData>
)