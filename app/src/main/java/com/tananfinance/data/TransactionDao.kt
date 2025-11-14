package com.tananfinance.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(tx: Transaction): Long

    @Update
    suspend fun update(tx: Transaction)

    @Delete
    suspend fun delete(tx: Transaction)

    @Query("SELECT * FROM transactions WHERE dateMillis BETWEEN :start AND :end ORDER BY dateMillis DESC")
    fun getTransactionsBetween(start: Long, end: Long): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE dateMillis BETWEEN :dayStart AND :dayEnd ORDER BY dateMillis DESC")
    fun getTransactionsForDay(dayStart: Long, dayEnd: Long): Flow<List<Transaction>>

    @Query("SELECT SUM(amount) FROM transactions WHERE isIncome = 0 AND dateMillis BETWEEN :start AND :end")
    fun getExpenseSumBetween(start: Long, end: Long): Flow<Double?>

    @Query("SELECT SUM(amount) FROM transactions WHERE isIncome = 1 AND dateMillis BETWEEN :start AND :end")
    fun getIncomeSumBetween(start: Long, end: Long): Flow<Double?>
}
