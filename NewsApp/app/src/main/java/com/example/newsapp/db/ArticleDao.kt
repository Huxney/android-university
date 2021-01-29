package com.example.newsapp.db

import androidx.room.*
import com.example.newsapp.model.Article
import retrofit2.http.DELETE

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): List<Article>

    @Query("DELETE FROM articles")
    suspend fun deleteArticle()
}