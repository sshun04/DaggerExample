package com.shunsukeshoji.daggerexample.di

import android.app.Application
import com.shunsukeshoji.daggerexample.BaseApplication
import com.shunsukeshoji.daggerexample.SessionManager
import com.shunsukeshoji.daggerexample.ui.main.posts.PostItem
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    fun sessionManager(): SessionManager

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}