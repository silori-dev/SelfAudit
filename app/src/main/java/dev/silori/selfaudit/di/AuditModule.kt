package dev.silori.selfaudit.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.silori.selfaudit.data.dao.AuditDao
import dev.silori.selfaudit.data.database.AuditDatabase
import dev.silori.selfaudit.data.repo.AuditRepo
import dev.silori.selfaudit.data.repo.AuditRepoImpl
import javax.inject.Singleton

@Module 
@InstallIn(SingletonComponent::class)
interface  AuditModule {

    @Singleton
    @Binds
    fun bindTaskRepository(repository : AuditRepoImpl) : AuditRepo

    companion object {
        @Singleton
        @Provides
        fun provideAuditDao(database: AuditDatabase): AuditDao =
            database.auditDao
    }
}