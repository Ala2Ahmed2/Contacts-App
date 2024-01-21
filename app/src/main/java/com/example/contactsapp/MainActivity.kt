package com.example.contactsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contactsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var name: String
    lateinit var phone: String
    lateinit var description: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
            if (!validateTextFields()) {
                return@setOnClickListener
            }
            onSaveButtonClick()
        }

    }

    private fun onSaveButtonClick() {
        name = binding.etName.text?.trim().toString()
        phone = binding.etPhone.text?.trim().toString()
        description = binding.etDescription.text?.trim().toString()
        val contact = Contact(name, phone, description,R.drawable.avatar)
        intent = Intent(this, ContactsActivity::class.java)
        intent.putExtra(Constants.contact, contact)
        startActivity(intent)
    }

    fun validateTextFields(): Boolean {
        name = binding.etName.text?.trim().toString()
        phone = binding.etPhone.text?.trim().toString()
        binding.etName.error = validateName(name)
        binding.etPhone.error = validatePhone(phone)
        return (validateName(name) == null && validatePhone(phone) == null)
    }

    fun validateName(name: String): String? {
        if (name.isEmpty())
            return "Required"
        if (name.trim().length < 3)
            return "Name cant be less than 3 characters"
        return null
    }

    fun validatePhone(phone: String): String? {
        if (phone.isEmpty())
            return "Required"
        return null

    }


}