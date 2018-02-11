package jhallman.splitter.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jhallman.splitter.R
import kotlinx.android.synthetic.main.activity_created_tab.*

class CreatedTabActivity : AppCompatActivity() {

    companion object {
        const val INTENT_TAB_TITLE = "Tab title"
        const val INTENT_TAB_ID = "Tab Id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_created_tab)
        loadTab()

        fab_edit_tab.setOnClickListener {
            val intent = Intent(this, EditTabActivity::class.java)
            intent.putExtra(EditTabActivity.INTENT_TAB_TITLE, tv_heading.text)
            intent.putExtra(EditTabActivity.INTENT_TAB_ID, 1)
            startActivity(intent)
        }

        fab_new_purchase.setOnClickListener {
            val intent = Intent(this, AddPurchaseActivity::class.java)
            startActivity(intent)
        }
    }

    fun loadTab() {
        val title = intent.getStringExtra(INTENT_TAB_TITLE)
        val id = intent.getIntExtra(INTENT_TAB_ID, 0)
        requireNotNull(id) { "no tab title provided in Intent extras" }

        tv_heading.text = title
        tv_heading.id = id
    }
}
