package com.example.jacob.splitter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.support.v4.widget.DrawerLayout
import android.view.View
import android.support.v7.widget.Toolbar


open class NavbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        val fullView = layoutInflater.inflate(R.layout.activity_navbar, null) as DrawerLayout
        val activityContainer = fullView.findViewById<View>(R.id.activity_content) as FrameLayout
        layoutInflater.inflate(layoutResID, activityContainer, true)
        super.setContentView(fullView)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        if (useToolbar()) {
            setSupportActionBar(toolbar)
            title = "Activity Title"
        } else {
            toolbar.visibility = View.GONE
        }
    }

    protected fun useToolbar(): Boolean {
        return true
    }
}
