package com.example.toolbar

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_toolbar.*

class ToolbarActivity : AppCompatActivity() {
    private lateinit var customToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)

        customToolbar = toolbar as androidx.appcompat.widget.Toolbar
        setSupportActionBar(customToolbar)
        supportActionBar?.title = "Custom Toolbar Title"
//        supportActionBar?.subtitle = "Subtitle"

        textview.setOnClickListener {
            openAnotherActivity()
        }
    }

    private fun openAnotherActivity() {
        val intent = Intent(this, AnimationActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    @SuppressLint("SetTextI18n")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.android -> {
                textview.text = "Clicked on Android"
                textview.setTextColor(resources.getColor(R.color.customGreen, resources.newTheme()))
            }
            R.id.cake -> {
                textview.text = "Clicked on Cake"
                textview.setTextColor(resources.getColor(R.color.customRed, resources.newTheme()))
            }
            R.id.fingerprint -> {
                textview.text = "Clicked on Fingerprint"
                textview.setTextColor(resources.getColor(R.color.black, resources.newTheme()))
            }
        }

        return super.onOptionsItemSelected(item)
    }
}