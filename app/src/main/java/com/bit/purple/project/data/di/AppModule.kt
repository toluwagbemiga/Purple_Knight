//package com.bit.purple.project.data.di
//
//import android.app.Application
//import androidx.room.Room
//import com.bit.purple.project.data.local.db.BankaDatabase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    @Singleton
//    fun provideBankaDatabase(app: Application): BankaDatabase {
//        return Room.databaseBuilder(
//            app,
//            BankaDatabase::class.java,
//            "banka_db"
//    }.fallbackToDestructiveMigration()
//            .build()
//
//
//}