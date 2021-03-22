package com.example.a20210322aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.a20210322aboutme.databinding.ActivityMainBinding

// Data Binding 可以取代 findViewById (省時間) //
// 用法： binding.(id_name) //
// DataBinding中 ‘id_name’ 的第二個字要大寫! //

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding  //ActivityMainBinding表示跟隨哪個layout
    private var myName: MyName = MyName("Edwina","linnnn")  //呼叫structure

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //DataBinding的必要條件 (相對於 setContentView)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //DataBinding的必要條件 (相對於 setContentView)

        binding.doneButton.setOnClickListener {
            addNickName(it)         //it 代表 button
        }

        binding.nicknameText.setOnClickListener {
            updateNickname(it)     //it 代表 當前輸入的nickname
        }

        binding.myName = myName         //使用data class綁定變數

    }

    private fun addNickName(view: View)                          //view 代表 button
    {
        binding.apply {                                         //節省打很多binding
            //nicknameText.text = binding.nicknameEdit.text     //way1 顯示輸入的內容
            myName?.nickname = nicknameEdit.text.toString()      //way2
            invalidateAll()                                     //refresh UI
            nicknameEdit.visibility = View.GONE                 //隱藏輸入欄
            doneButton.visibility = View.GONE                   //隱藏button
            nicknameText.visibility = View.VISIBLE
        }

        //隱藏鍵盤
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }

    private fun updateNickname(view: View)                      //view 代表 當前輸入的nickname
    {
        binding.apply {                                         //節省打很多binding
            nicknameEdit.visibility = View.VISIBLE
            doneButton.visibility = View.VISIBLE
            nicknameText.visibility = View.GONE

            // Set the focus to the edit text.
            binding.nicknameEdit.requestFocus()
        }

        //出現鍵盤
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.nicknameEdit, 0)

    }

}