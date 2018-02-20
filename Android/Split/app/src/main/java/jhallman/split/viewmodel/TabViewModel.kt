package jhallman.split.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import jhallman.split.service.model.Tab
import jhallman.split.service.model.TabDao

/**
 * Created by Jacob on 2018-02-19.
 */

/**
 * Created by Jacob on 2018-02-18.
 * Acts as a bridge between the View and the Model
 * Requests/aggregates data from the Model, and transforms it for the View
 */

class TabViewModel(val dataSource: TabDao) : ViewModel() {

    fun getTab(id: Long): LiveData<Tab> {
        return dataSource.findTabById(id)
    }
}