package com.example.apporgs.database.dao

import androidx.room.*
import com.example.apporgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun getAll(): List<Produto>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(produto: Produto)

    @Delete
    fun delete(produto: Produto)

    @Update
    fun update(produto: Produto)
}