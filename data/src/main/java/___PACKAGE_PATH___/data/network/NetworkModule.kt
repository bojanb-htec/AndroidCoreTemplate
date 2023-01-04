package ___PACKAGE_NAME___.data.network

import ___PACKAGE_NAME___.data.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

	companion object {
		private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
	}

	//    @Singleton
	//    @Provides
	//    internal fun provideExampleService(retrofit: Retrofit) = retrofit.create(ExampleApi::class.java)

	@Singleton
	@Provides
	internal fun provideRetrofit(client: OkHttpClient) =
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(client)
			.addConverterFactory(MoshiConverterFactory.create())
			.build()

	@Singleton
	@Provides
	internal fun provideClient(loggingInterceptor: HttpLoggingInterceptor) =
		OkHttpClient.Builder()
			.addInterceptor(loggingInterceptor)
			.build()

	@Singleton
	@Provides
	internal fun provideLoggingInterceptor() =
		HttpLoggingInterceptor().setLevel(
			if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
			else HttpLoggingInterceptor.Level.NONE
		)

	@Singleton
	@Provides
	internal fun providesMoshi() = Moshi.Builder().build()
}
