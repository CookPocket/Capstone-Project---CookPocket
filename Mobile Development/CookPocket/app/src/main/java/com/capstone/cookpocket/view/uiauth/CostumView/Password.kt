package com.capstone.cookpocket.view.uiauth.CostumView

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class Password : TextInputEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.toString().length < 8 || !s.toString().matches(".*[A-Za-z].*".toRegex()) ||
            !s.toString().matches(".*[0-9].*".toRegex()) || !s.toString().matches(".*[!@#$%^&*(),.?\":{}|<>].*".toRegex())) {
            setError("Password harus minimal 8 karakter, mengandung huruf, angka, dan karakter khusus", null)
        } else {
            error = null
        }
    }
}
