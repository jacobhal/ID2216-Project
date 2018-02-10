package jhallman.splitter.activities

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
}
