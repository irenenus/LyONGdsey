package com.irene.lyongdsey.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.irene.lyongdsey.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        onListeners()
    }

    private fun onListeners() {

        addStakeholderButton.setOnClickListener {
            startActivity(Intent(this, StakeholderActivity::class.java))
        }

        addProjectButton.setOnClickListener {
            startActivity(Intent(this, StakeholderActivity::class.java))
        }
    }
}