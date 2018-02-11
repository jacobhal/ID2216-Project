package jhallman.splitter.activities

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jhallman.splitter.R
import kotlinx.android.synthetic.main.activity_edit_purchase.*

class EditPurchaseActivity : AppCompatActivity() {

    companion object {
        const val INTENT_PURCHASE_TITLE = "Purchase title" // TODO: Remove later
        const val INTENT_PURCHASE_ID = "Purchase Id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_purchase)
        loadPurchase()

        fab_delete_purchase.setOnClickListener {
            showPurchaseDeleteDialog()
        }

        fab_save_purchase.setOnClickListener {

        }
    }


    // TODO: Load purchase from db
    fun loadPurchase() {
        val title = intent.getStringExtra(INTENT_PURCHASE_TITLE)
        val id = intent.getIntExtra(INTENT_PURCHASE_ID, 0)
        // requireNotNull(id) { "no purchase provided in Intent extras" }

        // TODO: Use resource string or separate textViews in xml file
        tv_subheading.text = title
        tv_subheading.id = id
    }

    fun showPurchaseDeleteDialog() {
        val dialogBuilder = AlertDialog.Builder(this, R.style.AlertDialogTheme)
        val titleString = getString(R.string.dialog_purchase_title) + ": " + tv_subheading.text

        dialogBuilder.setTitle(titleString)
        dialogBuilder.setMessage(R.string.dialog_purchase_message)
        dialogBuilder.setPositiveButton(R.string.dialog_purchase_delete, { dialog, whichButton ->
            // TODO: Save the purchase with this ID in db
        })
        dialogBuilder.setNegativeButton(R.string.dialog_purchase_cancel, { dialog, whichButton ->
            // User cancelled
        })
        val b = dialogBuilder.create()
        b.show()
    }
}
