package dev.silori.selfaudit.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audit_table")
data class AuditData(
    @PrimaryKey
    val date: String,
    val firstWork: String,
    val secondWork: String,
    val firstMess: String,
    val secondMess: String,
    val productivity: Int,
)