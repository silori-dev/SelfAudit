package dev.silori.selfaudit.data.repo

import dev.silori.selfaudit.data.entity.AuditData
import kotlinx.coroutines.flow.Flow

interface AuditRepo {

    suspend fun addAudit(audit: AuditData)

    fun getAllAudits(): Flow<List<AuditData>>

    fun getAuditByMonth(month: String, year: String): Flow<List<AuditData>>

    fun getAuditByYear(year : String): Flow<List<AuditData>>

// For custom date picker
    fun getAuditsWithinDates(initialDate: String, finalDate: String): Flow<List<AuditData>>

}