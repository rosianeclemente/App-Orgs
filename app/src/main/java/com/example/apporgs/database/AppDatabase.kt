package com.example.apporgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apporgs.database.converters.Converters
import com.example.apporgs.database.dao.ProdutoDao
import com.example.apporgs.database.dao.UsuarioDao
import com.example.apporgs.migrations.MIGRATION_1_2
import com.example.apporgs.model.Produto
import com.example.apporgs.model.Usuario

@Database(entities = [Produto::class,
                     Usuario::class], version = 2, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao
    abstract fun usuarioDao(): UsuarioDao

    companion object {

        @Volatile private var  db: AppDatabase? = null
        fun getInstance(context: Context) : AppDatabase {
            return db ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "orgs.db"
            ).addMigrations(MIGRATION_1_2)
                .build()
                .also {
                    db = it
                }
        }
    }
}