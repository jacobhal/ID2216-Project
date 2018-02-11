package jhallman.splitter.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jhallman.splitter.R
import kotlinx.android.synthetic.main.activity_edit_tab.*

class EditTabActivity : AppCompatActivity() {

    companion object {
        const val INTENT_TAB_TITLE = "Tab title" // TODO: Remove later
        const val INTENT_TAB_ID = "Tab Id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_tab)
        loadTab()

        // TODO: Replace templates with actual db fetch onClick

        fab_1.setOnClickListener {
            val intent = Intent(this, EditPurchaseActivity::class.java)
            intent.putExtra(EditPurchaseActivity.INTENT_PURCHASE_TITLE, tv_pizza.text)
            intent.putExtra(EditPurchaseActivity.INTENT_PURCHASE_ID, 1)
            startActivity(intent)
        }

        fab_2.setOnClickListener {
            val intent = Intent(this, EditPurchaseActivity::class.java)
            intent.putExtra(EditPurchaseActivity.INTENT_PURCHASE_TITLE, tv_beer.text)
            intent.putExtra(EditPurchaseActivity.INTENT_PURCHASE_ID, 2)
            startActivity(intent)
        }
    }

    // TODO: Load tab from db
    fun loadTab() {
        val title = intent.getStringExtra(INTENT_TAB_TITLE)
        val id = intent.getIntExtra(INTENT_TAB_ID, 0)
        requireNotNull(id) { "no tab provided in Intent extras" }

        tv_subheading.text = title
        tv_subheading.id = id
    }
}
