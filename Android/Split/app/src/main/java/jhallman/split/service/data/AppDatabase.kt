package jhallman.split.service.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import jhallman.split.service.model.ContactDao
import jhallman.split.service.model.PurchaseDao
import jhallman.split.service.model.ReceiptDao
import jhallman.split.service.model.TabDao
import jhallman.split.service.model.Contact
import jhallman.split.service.model.Purchase
import jhallman.split.service.model.Receipt
import jhallman.split.service.model.Tab

/**
 * Created by Jacob on 2018-02-16.
 */

@Database(entities = arrayOf(Tab::class, Receipt::class, Purchase::class, Contact::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tabDao(): TabDao
    abstract fun receiptDao(): ReceiptDao
    abstract fun purchaseDao(): PurchaseDao
    abstract fun contactDao(): ContactDao
}