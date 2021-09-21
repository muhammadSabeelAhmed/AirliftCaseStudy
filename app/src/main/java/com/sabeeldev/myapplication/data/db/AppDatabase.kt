package com.sabeeldev.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sabeeldev.myapplication.data.db.entities.CryptoData
import com.sabeeldev.myapplication.data.network.responses.CryptoRatesResponse

@Database(entities = [CryptoData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(contex: Context) =
            Room.databaseBuilder(
                contex.applicationContext,
                AppDatabase::class.java,
                "db_crypto.java"
            ).build()

    }
}