package com.example.animalcare.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.animalcare.database.daos.ley_dao
import com.example.animalcare.database.daos.org_dao
import com.example.animalcare.database.entities.ley_entity
import com.example.animalcare.database.entities.org_entity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ley_entity::class], version = 3, exportSchema = false)
public abstract class RoomDB : RoomDatabase() {
    abstract fun leyDao(): ley_dao


    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(context: Context,scope: CoroutineScope): RoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context, RoomDB::class.java, "animals")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomDBCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class RoomDBCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDB(database.leyDao())
                }
            }
        }

        suspend fun populateDB(
            leyDao: ley_dao

        ) {
            leyDao.deleteAll()

        }
    }
}