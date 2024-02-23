package dev.silori.selfaudit.data.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import dev.silori.selfaudit.data.database.AuditDatabase
import dev.silori.selfaudit.data.entity.AuditData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class AuditDaoTest {

    private lateinit var auditDao: AuditDao
    private lateinit var db: AuditDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AuditDatabase::class.java
        ).build()
        auditDao = db.auditDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun addAuditToDatabase() = runBlocking {
        val audit = AuditData("2023", "Good", "Good2", "Bad1", "Bad2", 3)
        auditDao.addAudit(audit)
        val auditData = auditDao.getAllAudits().first()

        assertThat(auditData).contains(audit)
    }

    @Test
    @Throws(Exception::class)
    fun getAllAudit() = runBlocking {
        val audit = AuditData("2023-01-01", "Good", "Good2", "Bad1", "Bad2", 3)
        val audit2 = AuditData("2023-01-02", "Good", "Good2", "Bad1", "Bad2", 4)
        auditDao.addAudit(audit)
        auditDao.addAudit(audit2)

        val auditData = auditDao.getAllAudits().test {
            val list = awaitItem()
            assertThat(list.size).isEqualTo(2)
            cancel()
        }
    }

    @Test
    fun getAuditByMonth() = runBlocking {
        val audit = AuditData("2023-01-01", "Good", "Good2", "Bad1", "Bad2", 3)
        val audit1 = AuditData("2023-01-02", "Good", "Good2", "Bad1", "Bad2", 3)
        val audit2 = AuditData("2023-02-03", "Good", "Good2", "Bad1", "Bad2", 3)
        val audit3 = AuditData("2023-02-04", "Good", "Good2", "Bad1", "Bad2", 3)
        auditDao.addAudit(audit)
        auditDao.addAudit(audit1)
        auditDao.addAudit(audit2)
        auditDao.addAudit(audit3)

        auditDao.getAuditByMonth("02", "2023").test {
            val list = awaitItem()

            assertThat(list).contains(audit2)
            assertThat(list).contains(audit3)

            assertThat(list.size).isEqualTo(2)
            cancel()
        }
    }

    @Test
    fun getAuditByYear() = runBlocking {
        val audit = AuditData("2023-01-01", "Good", "Good2", "Bad1", "Bad2", 3)
        val audit1 = AuditData("2023-01-02", "Good", "Good2", "Bad1", "Bad2", 3)
        val audit2 = AuditData("2024-02-04", "Good", "Good2", "Bad1", "Bad2", 3)
        val audit3 = AuditData("2024-03-06", "Good", "Good2", "Bad1", "Bad2", 3)
        val audit4 = AuditData("2024-03-05", "Good", "Good2", "Bad1", "Bad2", 3)
        auditDao.addAudit(audit)
        auditDao.addAudit(audit1)
        auditDao.addAudit(audit2)
        auditDao.addAudit(audit3)
        auditDao.addAudit(audit4)


        auditDao.getAuditByYear("2024").test {
            val list = awaitItem()

            assertThat(list).contains(audit2)
            assertThat(list).contains(audit3)
            assertThat(list).contains(audit4)

            assertThat(list.size).isEqualTo(3)
            cancel()
        }
    }

    @Test
    fun getAuditByWithinDates() = runBlocking {

        val audit = AuditData("2023-01-01", "Good", "Good2", "Bad1", "Bad2", 3)
        val audit1 = AuditData("2023-01-02", "Good", "Good2", "Bad1", "Bad2", 3)
        val audit2 = AuditData("2023-02-03", "Good", "Good2", "Bad1", "Bad2", 3)
        val audit3 = AuditData("2024-02-04", "Good", "Good2", "Bad1", "Bad2", 3)
        val audit4 = AuditData("2024-03-06", "Good", "Good2", "Bad1", "Bad2", 3)
        val audit5 = AuditData("2024-03-05", "Good", "Good2", "Bad1", "Bad2", 3)
        auditDao.addAudit(audit)
        auditDao.addAudit(audit1)
        auditDao.addAudit(audit2)
        auditDao.addAudit(audit3)
        auditDao.addAudit(audit4)
        auditDao.addAudit(audit5)


        auditDao.getAuditsWithinDates(initialDate = "2023-10-31", finalDate = "2024-02-05").test {
            val list = awaitItem()
            assertThat(list).contains(audit3)
            assertThat(list.size).isEqualTo(1)
            cancel()
        }

        auditDao.getAuditsWithinDates(initialDate = "2023-01-01", finalDate = "2023-05-21").test {
            val list = awaitItem()
            assertThat(list).contains(audit)
            assertThat(list).contains(audit1)
            assertThat(list).contains(audit2)
            assertThat(list.size).isEqualTo(3)
            cancel()

        }
    }

}