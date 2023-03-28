package ___PACKAGE_NAME___.presentation.di

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityBuilderModule {

    @Provides
    fun provideExampleActivity(activity: Activity): ExampleActivity = activity as ExampleActivity
}
