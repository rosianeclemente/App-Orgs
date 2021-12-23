package com.example.apporgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apporgs.database.converters.Converters
import com.example.apporgs.database.dao.ProdutoDao
import com.example.apporgs.model.Produto

@Database(entities = [Produto::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao

    companion object {

        @Volatile
        private lateinit var  db: AppDatabase
        fun getInstance(context: Context) : AppDatabase {
            if(::db.isInitialized) return db
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "AppOrgs_db"
            ).allowMainThreadQueries()
                .build().also {
                    db = it
                }
        }
    }
}