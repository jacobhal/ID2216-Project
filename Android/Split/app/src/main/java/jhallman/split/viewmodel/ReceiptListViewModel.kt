package jhallman.split.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import jhallman.split.service.model.Receipt
import jhallman.split.service.model.ReceiptDao


/**
 * Created by Jacob on 2018-02-18.
 * Acts as a bridge between the View and the Model
 * Requests/aggregates data from the Model, and transforms it for the View
 */

class ReceiptListViewModel(val dataSource: ReceiptDao) : ViewModel() {

    fun getReceipts(): LiveData<List<Receipt>> {
        return dataSource.getAllReceipts()
    }
}