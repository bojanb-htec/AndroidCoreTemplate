package ___PACKAGE_NAME___.presentation.di

import com.htecgroup.core.presentation.compose.composer.RouteComposer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RetainedModule {

    // @ActivityRetainedScoped
    // @Provides
    // fun provideExampleScreenHolderComposer(holderComposer: ExampleRouteComposer):
    //    RouteComposer<ExampleDestinations> = holderComposer
}
