package com.irene.lyongdsey.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.irene.lyongdsey.R
import com.irene.lyongdsey.utils.isValidEmail
import com.irene.lyongdsey.utils.isValidURL
import kotlinx.android.synthetic.main.activity_stakeholder.*

class StakeholderActivity : AppCompatActivity() {
    private var projectsList : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stakeholder)

        onListeners()
    }

    private fun onListeners() {
        backButton.setOnClickListener {
            finish()
        }

        submitButton.setOnClickListener {
            if (allTheTextFieldsAreValids()) {
                finish()
            }
            else {
                amountInputLayout.requestFocus()
                amountInputLayout.error = getString(R.string.stakeholder_fields_not_valid)
            }
        }

        fullNameEditText.setOnFocusChangeListener { _, focus ->
            if (!focus && fullNameEditText.text.isNullOrEmpty()) {
                fullNameInputLayout.error = getString(R.string.stakeholder_field_required)
            }
            else {
                fullNameInputLayout.error = null
            }
        }

        emailEditText.setOnFocusChangeListener { _, focus ->
            if (!focus && !emailEditText.text.isValidEmail()) {
                emailInputLayout.error = getString(R.string.stakeholder_email_not_valid)
            }
            else {
                emailInputLayout.error = null
            }
        }

        webEditText.setOnFocusChangeListener { _, focus ->
            if (!focus && !webEditText.text.toString().isValidURL()) {
                webInputLayout.error = getString(R.string.stakeholder_web_not_valid)
            }
            else {
                webInputLayout.error = null
            }
        }

        amountEditText.setOnFocusChangeListener { _, focus ->
            if (!focus && amountEditText.text.isNullOrEmpty()) {
                amountInputLayout.error = getString(R.string.stakeholder_field_required)
            }
            else {
                amountInputLayout.error = null
            }
        }

        projectsInputLayout.setEndIconOnClickListener {
            projectsList.add(projectsEditText.text.toString())

            projectsEditText.setText("")
            projectsTextView.visibility = View.VISIBLE
            projectsTextView.text = projectsList.toString().removePrefix("[").removeSuffix("]")
        }
    }

    private fun allTheTextFieldsAreValids() : Boolean {
       return !fullNameEditText.text.isNullOrEmpty() && emailEditText.text.toString().isValidEmail() && webEditText.text.toString().isValidURL() && !amountEditText.text.isNullOrEmpty()
    }
}