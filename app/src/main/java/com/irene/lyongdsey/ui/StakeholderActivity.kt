package com.irene.lyongdsey.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.irene.lyongdsey.R
import com.irene.lyongdsey.persistance.database.entity.StakeholderEntity
import com.irene.lyongdsey.repository.ProjectRepository
import com.irene.lyongdsey.repository.StakeholderRepository
import com.irene.lyongdsey.utils.isValidEmail
import com.irene.lyongdsey.utils.isValidURL
import kotlinx.android.synthetic.main.activity_stakeholder.*
import java.util.*

class StakeholderActivity : AppCompatActivity() {
    private var projectsList : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stakeholder)

        onListeners()
    }

    private fun onListeners() {
        // listener of buttons
        backButton.setOnClickListener {
            finish()
        }

        submitButton.setOnClickListener {
            if (allTheTextFieldsAreValid()) {
                // set the stakeholder to the DB
                    Thread {
                        // insert the stakeholder in to the DB, don't store the email because is sensible data
                        StakeholderRepository(application).addStakeholder(StakeholderEntity(
                            UUID.randomUUID(),
                            name = fullNameEditText.text.toString(),
                            website = webEditText.text.toString(),
                            projectFunded = projectsList,
                            amount = amountEditText.text.toString().toFloat()
                        ))
                    }.start()

                finish()
            }
            else {
                // go to the error text to indicate the user that something is not right
                amountInputLayout.requestFocus()
                amountInputLayout.error = getString(R.string.stakeholder_fields_not_valid)
            }
        }

        // do a error control of the fields
        fullNameEditText.setOnFocusChangeListener { _, focus ->
            // check if it has lost focus and it's empty
            if (!focus && fullNameEditText.text.isNullOrEmpty()) {
                fullNameInputLayout.error = getString(R.string.stakeholder_field_required)
            }
            else {
                fullNameInputLayout.error = null
            }
        }

        emailEditText.setOnFocusChangeListener { _, focus ->
            // check if it has lost focus and it's a valid email
            if (!focus && !emailEditText.text.isValidEmail()) {
                emailInputLayout.error = getString(R.string.stakeholder_email_not_valid)
            }
            else {
                emailInputLayout.error = null
            }
        }

        webEditText.setOnFocusChangeListener { _, focus ->
            // check if it has lost focus and it's a valid URL
            if (!focus && !webEditText.text.toString().isValidURL()) {
                webInputLayout.error = getString(R.string.stakeholder_web_not_valid)
            }
            else {
                webInputLayout.error = null
            }
        }

        amountEditText.setOnFocusChangeListener { _, focus ->
            // check if it has lost focus and it's not empty
            if (!focus && amountEditText.text.isNullOrEmpty()) {
                amountInputLayout.error = getString(R.string.stakeholder_field_required)
            }
            else {
                amountInputLayout.error = null
            }
        }

        projectsInputLayout.setEndIconOnClickListener {
            // add the project name to the current list
            projectsList.add(projectsEditText.text.toString())

            projectsEditText.setText("")
            projectsTextView.visibility = View.VISIBLE
            // show the list of projects
            projectsTextView.text = projectsList.toString().removePrefix("[").removeSuffix("]")
        }
    }

    private fun allTheTextFieldsAreValid() : Boolean {
       return !fullNameEditText.text.isNullOrEmpty() && emailEditText.text.toString().isValidEmail() && webEditText.text.toString().isValidURL() && !amountEditText.text.isNullOrEmpty()
    }
}