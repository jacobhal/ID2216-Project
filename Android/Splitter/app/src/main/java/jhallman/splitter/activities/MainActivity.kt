package jhallman.splitter.activities

import android.os.Bundle
import jhallman.splitter.R

import android.app.Activity
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_new_tab.setOnClickListener {
            val intent = Intent(this, CreatedTabActivity::class.java)
            startActivity(intent)
        }

        fab_all_tabs.setOnClickListener {
            val intent = Intent(this, AllTabsActivity::class.java)
            startActivity(intent)
        }
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

