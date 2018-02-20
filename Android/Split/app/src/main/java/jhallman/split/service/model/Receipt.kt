package jhallman.split.service.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Jacob on 2018-02-15.
 */

@Entity(tableName = "receipt",
        foreignKeys = arrayOf(ForeignKey(entity = Tab::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("tab_id"),
                onDelete = ForeignKey.CASCADE)))
data class Receipt(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        @ColumnInfo(name = "tab_id") val tabID: Int,
        @ColumnInfo(name = "receipt_title") val title: String)