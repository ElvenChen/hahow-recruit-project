package `in`.hahow.android_recruit_project.course

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import `in`.hahow.android_recruit_project.Moshi.moshi
import `in`.hahow.android_recruit_project.data.Course
import `in`.hahow.android_recruit_project.data.CourseData
import `in`.hahow.android_recruit_project.data.dataloader.DataLoader
import `in`.hahow.android_recruit_project.data.dataloader.JsonDataLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CourseViewModel(application: Application) : AndroidViewModel(application) {

    private val dataLoader: DataLoader

    private var course: Course? = null


    private val _courseListForAdapter = MutableLiveData<List<CourseData?>>()
    val courseListForAdapter: LiveData<List<CourseData?>>
        get() = _courseListForAdapter



    // function for getting course from the json file
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

            getCourseList(course)

        }

    }



    // function for arranging the raw Course data class instance and making a list for recyclerView
    // using.
    private fun getCourseList(course: Course?) {
        val courseList = mutableListOf<CourseData?>()

        course?.data?.forEach {
            courseList.add(it)
        }

        _courseListForAdapter.value = courseList
    }



    init {
        // initiating the JsonDataLoader instance to standby for Json data loading
        dataLoader = JsonDataLoader(application)

        // get the course list from the json file
        getCourse(moshi, "data.json")
    }
}