package com.capstone.cookpocket.view.uiauth.login.CostumView

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class Password: TextInputEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.toString().length < 8) {
            setError("Password tidak boleh kurang dari 8 karakter", null)
        } else {
            error = null
        }
    }
}