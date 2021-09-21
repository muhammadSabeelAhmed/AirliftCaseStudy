package com.sabeeldev.myapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sabeeldev.myapplication.data.db.entities.CryptoData

@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptoData(crypto: List<CryptoData>): Long

    @Query("SELECT * from CryptoData")
    fun getCryptoList(): LiveData<List<CryptoData>>

}