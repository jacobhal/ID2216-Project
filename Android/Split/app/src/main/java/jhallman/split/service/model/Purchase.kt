package jhallman.split.service.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Jacob on 2018-02-15.
 */

@Entity(tableName = "purchase",
        foreignKeys = arrayOf(
                ForeignKey(entity = Receipt::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("receipt_id"),
                onDelete = ForeignKey.CASCADE),

                ForeignKey(entity = Contact::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("contact_id"))
        ))
data class Purchase(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        @ColumnInfo(name = "receipt_id") val receiptID: Long,
        @ColumnInfo(name = "contact_id") val contactID: Long,
        @ColumnInfo(name = "purchase_price") val price: Int)