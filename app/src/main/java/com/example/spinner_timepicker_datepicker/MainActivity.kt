package com.example.spinner_timepicker_datepicker

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.core.view.isVisible
import com.example.spinner_timepicker_datepicker.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var calendar: Calendar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        calendar = Calendar.getInstance()
        setUpListenerClickSpinner()
        setUpListenerClickTimePicker()
        setUpListenerClickDatePicker()
    }


    @SuppressLint("NewApi", "SimpleDateFormat")
    private fun setUpListenerClickDatePicker() {
        binding.btnDatePicker.setOnClickListener {
            DatePickerDialog(this, { view, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                binding.tvDatePicker.text = SimpleDateFormat("dd-MM-yyyy").format(calendar.time)
            },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY),  calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    @SuppressLint("NewApi", "SimpleDateFormat")
    private fun setUpListenerClickTimePicker() {
        binding.btnTimePicker.setOnClickListener {
            TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE,minute)
                binding.tvTimePicker.text = SimpleDateFormat("HH:mm").format(calendar.time)
            },calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }
    }

    private fun setUpListenerClickSpinner() {
        with(binding){
            btnSpinner.setOnClickListener {
                spinner.isVisible = true
                showViewSpinner()
            }
        }
    }
    private fun showViewSpinner() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.tvSpinner.text = parent?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }


}