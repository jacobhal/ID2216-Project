package jhallman.split.service.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * Created by Jacob on 2018-02-16.
 */

@Dao
interface ReceiptDao {

    @Query("SELECT * FROM receipt")
    fun getAllReceipts(): LiveData<List<Receipt>>

    @Query("SELECT * FROM receipt WHERE id = :p0")
    fun findReceiptById(id: Long): LiveData<Receipt>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReceipt(receipt: Receipt)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateReceipt(receipt: Receipt)

    @Delete
    fun deleteReceipt(receipt: Receipt)
}