package dev.silori.selfaudit.data.repo

import android.util.Log
import dev.silori.selfaudit.data.dao.AuditDao
import dev.silori.selfaudit.data.entity.AuditData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuditRepoImpl @Inject constructor(private val auditDao: AuditDao) : AuditRepo {
    override suspend fun addAudit(audit: AuditData) {
        auditDao.addAudit(audit)
    }

    override fun getAllAudits(): Flow<List<AuditData>> =
        auditDao.getAllAudits()

    override fun getAuditByMonth(month: String, year: String): Flow<List<AuditData>> {
        return auditDao.getAuditByMonth(month, year)
    }

    override fun getAuditByYear(year: String): Flow<List<AuditData>> {
        return auditDao.getAuditByYear(year)
    }

    override fun getAuditsWithinDates(
        initialDate: String,
        finalDate: String
    ): Flow<List<AuditData>> = auditDao.getAuditsWithinDates(initialDate, finalDate)

}