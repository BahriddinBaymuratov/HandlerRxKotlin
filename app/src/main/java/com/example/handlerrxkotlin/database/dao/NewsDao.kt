package com.example.handlerrxkotlin.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.handlerrxkotlin.database.entity.NewsEntity
import io.reactivex.rxjava3.core.Flowable

@Dao
interface NewsDao {

    @Insert
    fun addNews(newsEntitiy: NewsEntity)

    @Query("select * from newsentity")
    fun getAllNews(): Flowable<List<NewsEntity>>
}