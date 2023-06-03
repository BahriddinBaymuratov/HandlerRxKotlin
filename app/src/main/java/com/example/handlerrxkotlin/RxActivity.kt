package com.example.handlerrxkotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.handlerrxkotlin.database.AppDatabase
import com.example.handlerrxkotlin.database.adapter.NewsAdapter
import com.example.handlerrxkotlin.database.entity.NewsEntity
import com.example.handlerrxkotlin.databinding.ActivityRxBinding
import io.reactivex.rxjava3.schedulers.Schedulers

class RxActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRxBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var adapter: NewsAdapter

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NewsAdapter()
        appDatabase = AppDatabase.getInstance(this)

        appDatabase.newsDao().getAllNews()
            .subscribeOn(Schedulers.io())
            .subscribe{
            adapter.submitList(it)
        }

        binding.apply {
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(this@RxActivity)

            btnSave.setOnClickListener {
                val title = title.text.toString()
                val desc = desc.text.toString()

                appDatabase.newsDao().addNews(NewsEntity(title = title, description = desc))
                Toast.makeText(this@RxActivity, "Saqlandi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}