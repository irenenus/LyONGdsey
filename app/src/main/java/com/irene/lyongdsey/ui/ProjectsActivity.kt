package com.irene.lyongdsey.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.irene.lyongdsey.R
import com.irene.lyongdsey.utils.isValidURL
import kotlinx.android.synthetic.main.activity_projects.*

class ProjectsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)

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

        descriptionEditText.setOnFocusChangeListener { _, focus ->
            if (!focus && descriptionEditText.text.isNullOrEmpty()) {
                descriptionInputLayout.error = getString(R.string.stakeholder_field_required)
            }
            else {
                descriptionInputLayout.error = null
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

        imageWebEditText.setOnFocusChangeListener { _, focus ->
            if (!focus && !imageWebEditText.text.toString().isValidURL()) {
                imageWebInputLayout.error = getString(R.string.stakeholder_web_not_valid)
            }
            else {
                imageWebInputLayout.error = null
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
    }

    private fun allTheTextFieldsAreValids() : Boolean {
       return fullNameEditText.text.toString().isNotEmpty() && descriptionEditText.text.toString().isNotEmpty()
               && webEditText.text.toString().isValidURL() && amountEditText.text.toString().isNotEmpty()
               && imageWebEditText.text.toString().isValidURL()
    }
}