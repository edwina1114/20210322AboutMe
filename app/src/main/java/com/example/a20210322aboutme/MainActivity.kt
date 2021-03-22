package com.example.a20210322aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

// Data Binding 可以取代 findViewById (省時間) //

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addNickName(it)         //it 代表 button
        }

        findViewById<TextView>(R.id.nickname_text).setOnClickListener {
            updateNickname(it)     //it 代表 當前輸入的nickname
        }

    }

    private fun addNickName(view: View)         //view 代表 button
    {
        val editText: EditText = findViewById(R.id.nickname_edit)           //way1
        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)   //way2

        nicknameTextView.text = editText.text           //顯示輸入的內容
        editText.visibility = View.GONE                 //隱藏輸入欄
        view.visibility = View.GONE                     //隱藏button
        nicknameTextView.visibility = View.VISIBLE

        //隱藏鍵盤
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }

    private fun updateNickname (view: View)     //view 代表 當前輸入的nickname
    {
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val doneButton = findViewById<Button>(R.id.done_button)

        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE

        // Set the focus to the edit text.
        editText.requestFocus()
        //出現鍵盤
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)

    }

}