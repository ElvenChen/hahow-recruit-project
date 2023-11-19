package `in`.hahow.android_recruit_project

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

// bind the image content into image view
@BindingAdapter("bindImage")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Log.i("test", "imgUrl = $imgUrl")

    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}


// bind the course status String into textView
@BindingAdapter("getCourseStatus")
fun getCourseStatus(textView: TextView, courseStatus: String) {
    when (courseStatus) {
        "INCUBATING" -> {
            textView.text = "募資中"
            textView.setBackgroundColor(ContextCompat.getColor(textView.context, R.color.orange))
        }
        "PUBLISHED" -> {
            textView.text = "已開課"
            textView.setBackgroundColor(ContextCompat.getColor(textView.context, R.color.teal_200))

        }
        else -> {
            textView.text = "未開課"
            textView.setBackgroundColor(ContextCompat.getColor(textView.context, R.color.teal_700))

        }
    }
}