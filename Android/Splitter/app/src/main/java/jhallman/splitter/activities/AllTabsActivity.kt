package jhallman.splitter.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jhallman.splitter.R
import kotlinx.android.synthetic.main.activity_all_tabs.*

class AllTabsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_tabs)


        // TODO: Replace templates with actual db fetch onClick

        fab_1.setOnClickListener {
            val intent = Intent(this, EditTabActivity::class.java)
            intent.putExtra(EditTabActivity.INTENT_TAB_TITLE, tv_bowling.text)
            intent.putExtra(EditTabActivity.INTENT_TAB_ID, 1)
            startActivity(intent)
        }

        fab_2.setOnClickListener {
            val intent = Intent(this, EditTabActivity::class.java)
            intent.putExtra(EditTabActivity.INTENT_TAB_TITLE, tv_tentapub.text)
            intent.putExtra(EditTabActivity.INTENT_TAB_ID, 2)
            startActivity(intent)
        }
    }
}
