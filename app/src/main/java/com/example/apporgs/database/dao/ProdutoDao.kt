package com.example.apporgs.database.dao

import androidx.room.*
import com.example.apporgs.model.Produto
import java.util.concurrent.Flow




@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    suspend fun buscaTodos() : List<Produto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(vararg produto: Produto)

    @Delete
    suspend fun remove(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :id")
    suspend fun buscaPorId(id: Long) : Produto?

}