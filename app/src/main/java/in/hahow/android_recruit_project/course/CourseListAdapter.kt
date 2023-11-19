package `in`.hahow.android_recruit_project.course

import android.annotation.SuppressLint
import android.app.Application
import android.content.res.ColorStateList
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import `in`.hahow.android_recruit_project.R
import `in`.hahow.android_recruit_project.data.CourseData
import `in`.hahow.android_recruit_project.databinding.ViewholderCourseListBinding
import `in`.hahow.android_recruit_project.ext.dateStringToLong
import `in`.hahow.android_recruit_project.ext.toDayLeft

class CourseListAdapter() :
    ListAdapter<CourseData, RecyclerView.ViewHolder>(CourseListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CourseViewHolder.from(parent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CourseViewHolder -> {
                val courseData = getItem(position) as CourseData
                holder.bind(courseData)
            }
        }
    }

    class CourseViewHolder(private val binding: ViewholderCourseListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SetTextI18n")
        fun bind(item: CourseData) {
            Log.i("test", "$item")

            // data binding
            binding.courseData = item
            binding.successCriteria = item.successCriteria



            // calculating progress bar
            if (item.numSoldTickets == 0) {

                binding.courseProgressBar.progress = 0

            } else if (item.numSoldTickets > 0) {

                if (item.numSoldTickets >= item.successCriteria.numSoldTickets) {

                    // if the sold ticket amount are bigger than criteria, than progress
                    // percentage must be 100.
                    binding.courseProgressBar.progress = 100

                    // setting progress bar color
                    binding.courseProgressBar.progressTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this.itemView.context, R.color.teal_200)
                    )

                } else {

                    // calculating the percentage of progress
                    val progress: Double = ((item.numSoldTickets).toDouble() / (item
                        .successCriteria.numSoldTickets).toDouble
                            ()) * 100.0
                    binding.courseProgressBar.progress = progress.toInt()

                    // setting progress bar color
                    binding.courseProgressBar.progressTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this.itemView.context, R.color.orange)
                    )

                }
            }



            // setting progress number
            if (item.numSoldTickets == 0) {

                binding.courseProgressNumber.text = "0%"


            } else if (item.numSoldTickets > 0) {

                if (item.numSoldTickets >= item.successCriteria.numSoldTickets) {

                    // if the sold ticket amount are bigger than criteria, than progress
                    // number must be 100.
                    binding.courseProgressNumber.text = "100%"

                } else {

                    binding.courseProgressNumber.text = "${item.numSoldTickets} / ${item.successCriteria.numSoldTickets} 人"

                }
            }



            // calculating proposalDueTime left time
            if (item.proposalDueTime != null) { // not every course data has proposalDueDate key,
                // so check if this key is exist for further calculating.

                // calculating how much time left to reach the due date
                val timeLeft = item.proposalDueTime.dateStringToLong().minus(System.currentTimeMillis())


                if (timeLeft <= 0) { // means current time is already over the deadline of proposal time

                    binding.courseDueTimeIcon.visibility = View.VISIBLE
                    binding.courseDueTime.visibility = View.VISIBLE
                    binding.courseDueTime.text = "倒數0天"

                } else if (timeLeft <= 1209600000){ // means current time to the deadline of
                    // proposal time is less than 14 days, so show the count-down information

                    binding.courseDueTimeIcon.visibility = View.VISIBLE
                    binding.courseDueTime.visibility = View.VISIBLE
                    binding.courseDueTime.text = "倒數${timeLeft.toDayLeft()}天"

                } else { // means current time to the deadline of
                    // proposal time is more than 14 days, so no need to show count-down information

                    binding.courseDueTimeIcon.visibility = View.GONE
                    binding.courseDueTime.visibility = View.GONE

                }

            } else { // means the course date doesn't has the proposalDueDate key, so no need to
                // show count-down information
                binding.courseDueTimeIcon.visibility = View.GONE
                binding.courseDueTime.visibility = View.GONE

            }



            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CourseViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewholderCourseListBinding.inflate(layoutInflater, parent, false)
                return CourseViewHolder(binding)
            }
        }
    }

    class CourseListDiffCallback :
        DiffUtil.ItemCallback<CourseData>() {
        override fun areItemsTheSame(oldItem: CourseData, newItem: CourseData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CourseData,
            newItem: CourseData
        ): Boolean {
            return oldItem == newItem
        }
    }
}