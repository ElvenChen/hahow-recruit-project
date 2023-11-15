package `in`.hahow.android_recruit_project.course

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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



        return binding.root
    }


}