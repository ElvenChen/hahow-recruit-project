package `in`.hahow.android_recruit_project.course

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import `in`.hahow.android_recruit_project.Moshi.moshi
import `in`.hahow.android_recruit_project.data.Course
import `in`.hahow.android_recruit_project.data.dataloader.DataLoader
import `in`.hahow.android_recruit_project.data.dataloader.JsonDataLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CourseViewModel(application: Application) : AndroidViewModel(application) {

    private val dataLoader: DataLoader

    private var course: Course? = null


    fun getCourse(moshi: Moshi, fileName: String) {

        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                try {
                    val jsonString = dataLoader.loadData(fileName)
                    val adapter = moshi.adapter(Course::class.java)
                    course = adapter.fromJson(jsonString)
                } catch (exception: Exception) {
                    Log.i("test", "something went wrong ")
                }
            }

            Log.i("test", "$course")
            Log.i("test", "${course?.data?.size}")
        }

    }


    init {
        dataLoader = JsonDataLoader(application)
        getCourse(moshi, "data.json")
    }
}