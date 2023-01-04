package ___PACKAGE_NAME___.app.di

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CoreAppModule {

	@Provides
	internal fun provideResources(@ApplicationContext context: Context): Resources =
		context.resources
}
