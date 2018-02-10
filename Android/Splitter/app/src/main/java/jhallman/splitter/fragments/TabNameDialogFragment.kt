package jhallman.splitter.fragments

import android.os.Bundle

import jhallman.splitter.R
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Intent
import jhallman.splitter.activities.CreatedTabActivity


class TabNameDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(getActivity())
        builder.setMessage(R.string.dialog_tab_message)
                .setTitle(R.string.dialog_tab_title)
                .setPositiveButton(R.string.dialog_tab_create, { dialog, id ->
                    // TODO: Create tab and add it to db with an id
                    val intent = Intent(activity, CreatedTabActivity::class.java)
                    // TODO: Pass ID and Title from db
                    intent.putExtra(CreatedTabActivity.INTENT_TAB_TITLE, "Movie Night at Jacob's Place")
                    intent.putExtra(CreatedTabActivity.INTENT_TAB_ID, 1)
                    startActivity(intent)
                })
                .setNegativeButton(R.string.dialog_tab_cancel, { dialog, id ->
                    // User cancelled the dialog
                })
        // Create the AlertDialog object and return it
        return builder.create()
    }
}
