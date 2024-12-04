package com.capstone.cookpocket.view.uiauth.CostumView


import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText

class ConfirmPassword : TextInputEditText {
    private var originalPasswordField: EditText? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun setPasswordField(passwordField: EditText) {
        originalPasswordField = passwordField
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val originalPassword = originalPasswordField?.text?.toString()

        if (originalPassword != null && s.toString() != originalPassword) {
            error = "Konfirmasi password harus sama dengan password"
        } else {
            error = null
        }
    }
}
