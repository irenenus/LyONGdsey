package com.irene.lyongdsey.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.irene.lyongdsey.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    private var totalFundedProjects: Int = 0
    private var totalStakeholders: Int = 0
    private var totalMoney: Float = 0f
    private var averageFundsPerProject: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setUpView()
        onListeners()
    }

    private fun setUpView(){
        numFundedProjects.text = totalFundedProjects.toString()
        numStakeholders.text = totalStakeholders.toString()
        raisedMoney.text = "$totalMoney €"
        averageFunds.text = "$averageFundsPerProject €"

    }

    private fun onListeners() {

        addStakeholderButton.setOnClickListener {
            startActivity(Intent(this, StakeholderActivity::class.java))
        }

        addProjectButton.setOnClickListener {
            startActivity(Intent(this, ProjectsActivity::class.java))
        }
    }
}