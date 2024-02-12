package dev.silori.selfaudit.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.silori.selfaudit.data.dataStore.DataStoreManager
import dev.silori.selfaudit.data.database.AuditDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
        ) = DataStoreManager(context = context)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AuditDatabase =
        Room
            .databaseBuilder(appContext, AuditDatabase::class.java, AuditDatabase.DATABASE_NAME)
            .build()
}