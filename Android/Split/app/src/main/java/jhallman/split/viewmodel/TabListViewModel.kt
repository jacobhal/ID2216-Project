package jhallman.split.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import jhallman.split.service.model.Tab
import jhallman.split.service.model.TabDao
import javax.inject.Inject


/**
 * Created by Jacob on 2018-02-18.
 * Acts as a bridge between the View and the Model
 * Requests/aggregates data from the Model, and transforms it for the View
 */

class TabListViewModel @Inject constructor (val dataSource: TabDao) : ViewModel() {

    private lateinit var mTabs: LiveData<List<Tab>>
    private lateinit var mTab: LiveData<Tab>

    fun getTabs(): LiveData<List<Tab>> {
        return dataSource.getAllTabs()
    }
}