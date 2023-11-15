package `in`.hahow.android_recruit_project.data.dataloader

import android.app.Activity
import android.app.Application
import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

class JsonDataLoader(private val context : Context) : DataLoader {
    override fun loadData(fileName: String): String {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        return reader.readText()
    }
}