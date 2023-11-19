package `in`.hahow.android_recruit_project.course

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import `in`.hahow.android_recruit_project.Moshi
import `in`.hahow.android_recruit_project.R
import `in`.hahow.android_recruit_project.data.dataloader.JsonDataLoader
import `in`.hahow.android_recruit_project.databinding.FragmentCourseBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class CourseFragment : Fragment() {

    private val viewModel: CourseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // handle binding
        val binding = FragmentCourseBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner



        // setting recyclerView adapter
        binding.courseRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        val adapter = CourseListAdapter()
        binding.courseRecyclerView.adapter = adapter



        //observe course list for adapter
        viewModel.courseListForAdapter.observe(viewLifecycleOwner){
            Log.i("test", "observe live data from viewModel = $it")

            adapter.submitList(it)
            binding.layoutSwipeRefreshCourse.isRefreshing = false
        }



        // setting drop down refresh all course
        val listener = SwipeRefreshLayout.OnRefreshListener {
            viewModel.getCourse(Moshi.moshi, "data.json")
            binding.layoutSwipeRefreshCourse.isRefreshing = true
        }
        binding.layoutSwipeRefreshCourse.setOnRefreshListener(listener)



        return binding.root
    }


}