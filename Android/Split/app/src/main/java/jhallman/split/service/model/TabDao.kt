package jhallman.split.service.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

/**
 * Created by Jacob on 2018-02-16.
 */

@Dao
interface TabDao {

    @Query("SELECT * FROM tab")
    fun getAllTabs(): LiveData<List<Tab>>

    @Query("SELECT * FROM tab WHERE id = (:tabID)")
    fun findTabById(tabID: Long): LiveData<Tab>

    @Insert(onConflict = REPLACE)
    fun insertTab(tab: Tab)

    @Update(onConflict = REPLACE)
    fun updateTab(tab: Tab)

    @Delete
    fun deleteTab(tab: Tab)
}