package com.example.apporgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.apporgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun getAll(): List<Produto>

    @Insert
    fun insert(produto: Produto)
}