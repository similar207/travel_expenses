package com.example.travel_expenses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.travel_expenses.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var mBinding: ActivityMainBinding
    private val model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mBinding.setting.setOnClickListener(){
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("필터")

            val  mAlertDialog = mBuilder.show()
            val okButton = mDialogView.findViewById<Button>(R.id.search_btn)
            okButton.setOnClickListener {
                val title = mDialogView.findViewById<EditText>(R.id.exchange).text.toString()
                model.set_money(title)
                mAlertDialog.dismiss()
            }

            val noButton = mDialogView.findViewById<Button>(R.id.closeButton)
            noButton.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

        mBinding.lifecycleOwner = this
        mBinding.viewModel = model
    }
}