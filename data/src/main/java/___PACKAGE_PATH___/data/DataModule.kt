package ___PACKAGE_NAME___.data

import ___PACKAGE_NAME___.data.db.DatabaseModule
import ___PACKAGE_NAME___.data.network.NetworkModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, NetworkModule::class])
@InstallIn(SingletonComponent::class)
interface DataModule {

	//    @Singleton
	//    @Binds
	//    fun bindExampleRepository(exampleRepository: ExampleRepositoryImpl): ExampleRepository

}
