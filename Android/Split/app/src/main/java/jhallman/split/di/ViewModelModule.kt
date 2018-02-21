package jhallman.split.di

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import jhallman.split.viewmodel.TabViewModel

/**
 * Created by Jacob on 2018-02-21.
 */

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TabViewModel::class)
    abstract fun bindTabViewModel(viewModel: TabViewModel): ViewModel
}