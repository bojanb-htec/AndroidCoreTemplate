package ___PACKAGE_NAME___.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    // @Singleton
    // @Provides
    // internal fun provideExampleDao(database: ___APP_NAME_CAMEL___Database): ExampleDao = database.exampleDao()

    @Singleton
    @Provides
    internal fun provideDatabase(@ApplicationContext context: Context): ___APP_NAME_CAMEL___Database =
        Room.databaseBuilder(context, ___APP_NAME_CAMEL___Database::class.java, ___APP_NAME_CAMEL___Database.NAME)
            .addMigrations(Migrations.MIGRATION_1_2)
            .build()

    @Singleton
    @InMemoryDatabase
    @Provides
    internal fun provideInMemoryDatabase(@ApplicationContext context: Context): ___APP_NAME_CAMEL___Database =
        Room.inMemoryDatabaseBuilder(context, ___APP_NAME_CAMEL___Database::class.java)
            .addMigrations(Migrations.MIGRATION_1_2)
            .build()

    @Qualifier
    @kotlin.annotation.Retention(value = AnnotationRetention.RUNTIME)
    annotation class InMemoryDatabase
}
