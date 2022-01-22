package com.irene.lyongdsey.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.irene.lyongdsey.R
import com.irene.lyongdsey.repository.ProjectRepository
import com.irene.lyongdsey.repository.StakeholderRepository
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DashboardActivity : AppCompatActivity() {
    private var totalFundedProjects: Int = 0
    private var totalStakeholders: Int = 0
    private var totalMoney: Float = 0f
    private var averageFundsPerProject: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setUpProjectView()
        setUpStakeholderView()
        onListeners()
    }

    override fun onResume() {
        super.onResume()
        getProjects()
        getStakeholders()
    }

    private fun getProjects() {
        MainScope().launch {
            withContext(Dispatchers.Default) {
                // get projects from DB
                ProjectRepository(application).getAllProjects()?.also { projects ->
                    totalFundedProjects = projects.size
                    // get total funds
                    var totalFunds = 0.0
                    projects.forEach {
                        if (it.completed) {
                            totalFunds += it.budget
                        }
                    }
                    if (projects.isNotEmpty()) {
                        // calculate the average of the funds
                        val averageFunds = totalFunds / projects.size
                        averageFundsPerProject = averageFunds.toFloat()
                    }
                }
            }
            setUpProjectView()
        }
    }

    private fun getStakeholders(){
        MainScope().launch {
            withContext(Dispatchers.Default) {
                StakeholderRepository(application).getAllStakeholders()?.also { stakeholders ->
                    totalMoney = 0F
                    stakeholders.forEach {
                        totalMoney += it.amount
                    }
                    totalStakeholders = stakeholders.size
                }
            }
            setUpStakeholderView()
        }
    }

    // Set up view with the current params
    private fun setUpProjectView(){
        numFundedProjects.text = totalFundedProjects.toString()
        averageFunds.text = "$averageFundsPerProject €"
    }

    // Set up view with the current params
    private fun setUpStakeholderView(){
        numStakeholders.text = totalStakeholders.toString()
        raisedMoney.text = "$totalMoney €"
    }

    // Set listeners of buttons
    private fun onListeners() {
        addStakeholderButton.setOnClickListener {
            startActivity(Intent(this, StakeholderActivity::class.java))
        }

        addProjectButton.setOnClickListener {
            startActivity(Intent(this, ProjectsActivity::class.java))
        }
    }
}