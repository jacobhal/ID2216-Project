package jhallman.splitter.activities

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
    }

    fun loadTab() {
        val title = intent.getStringExtra(INTENT_TAB_TITLE)
        val id = intent.getIntExtra(INTENT_TAB_ID, 0)
        requireNotNull(id) { "no tab title provided in Intent extras" }

        tv_heading.text = title
        tv_heading.id = id
    }
}
