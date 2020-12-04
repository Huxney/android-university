package com.example.midterm2

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.midterm2.networking.CustomCallback
import com.example.midterm2.database.AppDatabase
import com.example.midterm2.database.User
import com.example.midterm2.networking.HttpRequest
import com.example.midterm2.networking.ListModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var customToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customToolbar = toolbar as androidx.appcompat.widget.Toolbar
        setSupportActionBar(customToolbar)
        supportActionBar?.title = "Custom Toolbar Title"

        HttpRequest.getRequest(HttpRequest.PATH, object : CustomCallback {
            override fun onSuccess(body: String) {
                val listModel = Gson().fromJson(body, arrayOf<ListModel>()::class.java).toList()

                GlobalScope.launch(Dispatchers.IO) {
                    listModel.forEach { user ->
                        AppDatabase.getDatabase(this@MainActivity).userDao().insertAll(
                            User(user.firstname, user.lastname, user.skills.toString())
                        )
                    }

                    d(
                        "TestLog",
                        "${
                            AppDatabase.getDatabase(this@MainActivity).userDao().getAll()
                        }"
                    )
                }
            }
        })

        valueAnimation(textView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.android -> {
                val intent = Intent(this, ViewPagerActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun valueAnimation(view: View) {
        val valueAnim = ValueAnimator.ofFloat(0f, 50f)
        valueAnim.duration = 250L
        valueAnim.addUpdateListener { view.translationX = it.animatedValue as Float }
        valueAnim.repeatCount = ValueAnimator.INFINITE
        valueAnim.repeatMode = ValueAnimator.REVERSE
        valueAnim.start()
    }
}