package dev.silori.selfaudit.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.silori.selfaudit.data.entity.AuditData
import kotlinx.coroutines.flow.Flow

@Dao
interface AuditDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAudit(audit: AuditData)

    @Query("Select * from audit_table ORDER BY date DESC")
    fun getAllAudits(): Flow<List<AuditData>>

    @Query("SELECT * FROM audit_table WHERE date LIKE :year||'-'||:month||'-'||'__' ORDER BY date ")
    fun getAuditByMonth(month: String, year: String): Flow<List<AuditData>>

    @Query("SELECT * FROM audit_table WHERE date LIKE :year||'%' ORDER BY date")
    fun getAuditByYear(year : String) : Flow<List<AuditData>>

    @Query("SELECT * FROM audit_table WHERE date BETWEEN :initialDate AND :finalDate ORDER BY date ")
    fun getAuditsWithinDates(initialDate: String, finalDate: String): Flow<List<AuditData>>

}