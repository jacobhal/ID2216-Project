package jhallman.splitter.activities

import android.os.Bundle
import jhallman.splitter.R

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import jhallman.splitter.fragments.TabNameDialogFragment
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_new_tab.setOnClickListener {
            showTabCreationDialog()
        }

        fab_all_tabs.setOnClickListener {
            val intent = Intent(this, AllTabsActivity::class.java)
            startActivity(intent)
        }
    }

    fun showTabCreationDialog() {
        val dialogBuilder = AlertDialog.Builder(this, R.style.AlertDialogTheme)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog, null)
        dialogBuilder.setView(dialogView)

        val edt = dialogView.findViewById(R.id.edit1) as EditText

        dialogBuilder.setTitle(R.string.dialog_tab_title)
        dialogBuilder.setPositiveButton(R.string.dialog_tab_create, { dialog, whichButton ->
            // Get the value from EditText field
            val tabName = "" + edt.text
            // TODO: Create tab and add it to db with an id
            val intent = Intent(this, CreatedTabActivity::class.java)
            // TODO: Pass ID and Title from db
            intent.putExtra(CreatedTabActivity.INTENT_TAB_TITLE, tabName)
            intent.putExtra(CreatedTabActivity.INTENT_TAB_ID, 1)
            startActivity(intent)
        })
        dialogBuilder.setNegativeButton(R.string.dialog_tab_cancel, { dialog, whichButton ->
            // User cancelled
        })
        val b = dialogBuilder.create()
        b.show()
    }

    /*
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent com.example.jacob.com.example.jacob.com.example.jacob.splitter.com.example.jacob.com.example.jacob.splitter.activity in AndroidManifest.xml.
        val id = item.itemId

        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }
    */
}

