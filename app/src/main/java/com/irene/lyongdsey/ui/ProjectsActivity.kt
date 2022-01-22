package com.irene.lyongdsey.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.irene.lyongdsey.R
import com.irene.lyongdsey.persistance.database.entity.ProjectEntity
import com.irene.lyongdsey.repository.ProjectRepository
import com.irene.lyongdsey.utils.isValidURL
import kotlinx.android.synthetic.main.activity_projects.*
import kotlinx.android.synthetic.main.activity_projects.amountEditText
import kotlinx.android.synthetic.main.activity_projects.amountInputLayout
import kotlinx.android.synthetic.main.activity_projects.backButton
import kotlinx.android.synthetic.main.activity_projects.fullNameEditText
import kotlinx.android.synthetic.main.activity_projects.fullNameInputLayout
import kotlinx.android.synthetic.main.activity_projects.submitButton
import kotlinx.android.synthetic.main.activity_projects.webEditText
import kotlinx.android.synthetic.main.activity_projects.webInputLayout
import kotlinx.android.synthetic.main.activity_stakeholder.*
import java.util.*

class ProjectsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)

        onListeners()
    }

    private fun onListeners() {
        // add listeners of buttons
        backButton.setOnClickListener {
            finish()
        }

        submitButton.setOnClickListener {
            // check that there's no error
            if (allTheTextFieldsAreValid()) {
                // add the project to database
                Thread {
                    // insert the project to the DB
                    ProjectRepository(application).addProject(
                        ProjectEntity(
                            UUID.randomUUID(),
                            name = fullNameEditText.text.toString(),
                            budget = amountEditText.text.toString().toFloat(),
                            completed = true
                        )
                    )
                }.start()
                finish()
            } else {
                // go to the error text to indicate the user that something is not right
                amountInputLayout.requestFocus()
                amountInputLayout.error = getString(R.string.stakeholder_fields_not_valid)
            }
        }

        // set error control for each field
        fullNameEditText.setOnFocusChangeListener { _, focus ->
            // check if it has lost the focus and the text is not empty
            if (!focus && fullNameEditText.text.isNullOrEmpty()) {
                fullNameInputLayout.error = getString(R.string.stakeholder_field_required)
            } else {
                fullNameInputLayout.error = null
            }
        }

        descriptionEditText.setOnFocusChangeListener { _, focus ->
            // check if it has lost the focus and the text is not empty
            if (!focus && descriptionEditText.text.isNullOrEmpty()) {
                descriptionInputLayout.error = getString(R.string.stakeholder_field_required)
            } else {
                descriptionInputLayout.error = null
            }
        }

        webEditText.setOnFocusChangeListener { _, focus ->
            // check if it has lost the focus and it's a valid URL
            if (!focus && !webEditText.text.toString().isValidURL()) {
                webInputLayout.error = getString(R.string.stakeholder_web_not_valid)
            } else {
                webInputLayout.error = null
            }
        }

        imageWebEditText.setOnFocusChangeListener { _, focus ->
            // check if it has lost the focus and it's a valid URL
            if (!focus && !imageWebEditText.text.toString().isValidURL()) {
                imageWebInputLayout.error = getString(R.string.stakeholder_web_not_valid)
            } else {
                imageWebInputLayout.error = null
            }
        }

        amountEditText.setOnFocusChangeListener { _, focus ->
            // check if it has lost the focus and the text is not empty
            if (!focus && amountEditText.text.isNullOrEmpty()) {
                amountInputLayout.error = getString(R.string.stakeholder_field_required)
            } else {
                amountInputLayout.error = null
            }
        }
    }

    private fun allTheTextFieldsAreValid(): Boolean {
        return fullNameEditText.text.toString().isNotEmpty() && descriptionEditText.text.toString()
            .isNotEmpty()
                && webEditText.text.toString().isValidURL() && amountEditText.text.toString()
            .isNotEmpty()
                && imageWebEditText.text.toString().isValidURL()
    }
}