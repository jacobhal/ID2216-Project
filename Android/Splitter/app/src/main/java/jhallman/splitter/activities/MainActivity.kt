package jhallman.splitter.activities

import android.os.Bundle
import jhallman.splitter.R

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: Setup proper navigation drawer with DrawerUtil class
        /*
        val drawer = DrawerBuilder()
        drawer.withActivity(this)
        drawer.build()
        */

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

        edt.onFocusChangeListener = object : OnFocusChangeListener {
            override fun onFocusChange(v: View, hasFocus: Boolean) {
                edt.post(Runnable {
                    val inputMethodManager = this@MainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.showSoftInput(edt, InputMethodManager.SHOW_IMPLICIT)
                })
            }
        }
        edt.requestFocus()

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

        edt.requestFocus()

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

