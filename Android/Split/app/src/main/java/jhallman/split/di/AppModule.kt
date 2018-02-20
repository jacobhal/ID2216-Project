package jhallman.split.di

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import jhallman.split.service.data.AppDatabase
import javax.inject.Singleton

/**
 * Created by Jacob on 2018-02-16.
 * In Dagger 2, classes annotated with @Module are responsible for providing objects which can be injected.
 * The @Module annotation tells Dagger that the AppModule class will provide dependencies for a part of the application.
 * It is normal to have multiple Dagger modules in a project, and it is typical for one of them to provide app-wide dependencies.
 */

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesAppContext() = context

    @Provides fun providesAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "tab-db").allowMainThreadQueries().build()

    @Provides fun providesTabDao(database: AppDatabase) = database.tabDao()
    @Provides fun providesReceiptDao(database: AppDatabase) = database.receiptDao()
    @Provides fun providesPurchaseDao(database: AppDatabase) = database.purchaseDao()
    @Provides fun providesContactDao(database: AppDatabase) = database.contactDao()

}