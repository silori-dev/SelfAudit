package dev.silori.selfaudit.data.database


import androidx.room.*
import dev.silori.selfaudit.data.dao.AuditDao
import dev.silori.selfaudit.data.entity.AuditData

@Database(
    entities = [AuditData::class],
    version = AuditDatabase.DATABASE_VERSION,
    exportSchema = true,
)
abstract class AuditDatabase : RoomDatabase() {

    abstract val auditDao : AuditDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "audit_database"
    }
}
