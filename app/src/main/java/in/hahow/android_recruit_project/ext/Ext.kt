package `in`.hahow.android_recruit_project.ext

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

// transform String of ISO-8601 date to Long of date
@RequiresApi(Build.VERSION_CODES.O)
fun String.dateStringToLong(): Long {
    val formatter = DateTimeFormatter.ISO_INSTANT
    val instant = Instant.from(formatter.parse(this))
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

    return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}
// transform Long of time left to Long of day left (cut all number after point)
fun Long.toDayLeft(): Int {
    return (this / (1000 * 60 * 60 * 24)).toInt()
}