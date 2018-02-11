package jhallman.splitter.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jhallman.splitter.R
import kotlinx.android.synthetic.main.activity_add_purchase.*

class AddPurchaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_purchase)

        fab_add_person.setOnClickListener {
            val intent = Intent(this, AddPersonActivity::class.java)
            startActivity(intent)
        }

        fab_add_contact_1.setOnClickListener {
            // TODO: Add onclickListeners for all contacts when fetching contacts from phone works
        }
    }
}
