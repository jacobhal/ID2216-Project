package jhallman.split.viewmodel

import android.arch.lifecycle.*
import android.support.v4.app.FragmentActivity
import jhallman.split.service.model.Tab
import jhallman.split.service.model.TabDao
import javax.inject.Inject

/**
 * Created by Jacob on 2018-02-19.
 */

/**
 * Created by Jacob on 2018-02-18.
 * Acts as a bridge between the View and the Model
 * Requests/aggregates data from the Model, and transforms it for the View
 */

class TabViewModel @Inject constructor (val tabDao: TabDao): ViewModel() {

    private lateinit var mTab: LiveData<Tab>

    fun getAllTabs() {
        tabDao.getAllTabs()
    }

    fun testHello(): String {
        return "Hello"
    }

    companion object{
        fun create(activity: FragmentActivity): TabViewModel {
            var tabViewModel = ViewModelProviders.of(activity).get(TabViewModel::class.java)
            return tabViewModel
        }
    }
}