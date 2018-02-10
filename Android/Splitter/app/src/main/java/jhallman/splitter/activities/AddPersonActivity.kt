package jhallman.splitter.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jhallman.splitter.R
import kotlinx.android.synthetic.main.activity_add_person.*

class AddPersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)

        fab_add_contact.setOnClickListener {
            // TODO: Load phone contacts
        }

        fab_add_person.setOnClickListener {
            // TODO: Add person to Tab if all fields are filled in
        }
    }
}
