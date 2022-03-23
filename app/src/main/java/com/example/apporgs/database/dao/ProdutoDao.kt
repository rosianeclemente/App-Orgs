package com.example.apporgs.database.dao

import androidx.room.*
import com.example.apporgs.model.Produto
import java.util.concurrent.Flow

import kotlinx.coroutines.flow.collect


@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos() : Flow<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(vararg produto: Produto)

    @Delete
    suspend fun remove(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :id")
    fun buscaPorId(id: Long) : Flow<Produto?>

}