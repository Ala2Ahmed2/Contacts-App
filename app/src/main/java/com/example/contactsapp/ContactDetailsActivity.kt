package com.example.contactsapp

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contactsapp.databinding.ActivityContactDetailsBinding

class ContactDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityContactDetailsBinding
    var contactDetailsObj:Contact? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        extractParams()
        binding.contactDetailsImv.setImageResource(contactDetailsObj?.image?:R.drawable.avatar)
    }
    private fun extractParams() {
        contactDetailsObj = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.contactDetails, Contact::class.java)
        } else {
            intent.getParcelableExtra<Contact>(Constants.contactDetails) as Contact
        }
    }
}