package com.valentine.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.valentine.sharedpreferences.databinding.ActivityMainBinding

//global variable
private val sharedPrefFile = "kotlinsharedpreference"

class MainActivity : AppCompatActivity() {
    //object at method oncreate
    private val SharedPrefFile = "kotlinsharedpreference"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        setContentView(R.layout.activity_main)
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(SharedPrefFile, Context.MODE_PRIVATE)

        //Save ID and Name located at EditText into SharedPreferences
        binding.btnSave.setOnClickListener {
            val id: Int = Integer.parseInt(binding.etInputId.text.toString())
            val name: String = binding.etInputName.text.toString()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("id_key", id)
            editor.putString("name_key", name)
            editor.apply()
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
        }

        //get data ID and name from SharedPreferences
        binding.btnView.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt("id_key", 0)
            val sharedNameValue = sharedPreferences.getString("name_key", "defaultname")
            if (sharedIdValue.equals(0) && sharedNameValue.equals("defaultname")) {
                binding.tvShowName.setText("default name: ${sharedNameValue}").toString()
                binding.tvShowId.setText("default id: ${sharedIdValue.toString()}")
                Toast.makeText(this, "Data View Kosong", Toast.LENGTH_SHORT).show()
            } else {
                binding.tvShowName.setText(sharedNameValue).toString()
                binding.tvShowId.setText(sharedIdValue.toString())
                Toast.makeText(this, "Data View ditampilkan", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        //clear data all SharedPreferences
        binding.btnClear.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            binding.tvShowName.setText("")
            binding.tvShowId.setText("")
            Toast.makeText(this, "Data Clear", Toast.LENGTH_SHORT).show()
        }
    }
}