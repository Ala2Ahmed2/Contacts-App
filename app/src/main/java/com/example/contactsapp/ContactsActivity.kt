package com.example.contactsapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.example.contactsapp.databinding.ActivityContactsBinding


class ContactsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactsBinding
    private var contactsList= mutableListOf<Contact>()
    private lateinit var adapter: ContactsRecyclerAdapter
    private var contactObj:Contact? =null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        extractParams()
        initRecyclerView()


    }
    private fun extractParams() {
        val intent = intent
        contactObj = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            IntentCompat.getParcelableExtra(intent, Constants.contact, Contact::class.java)
        } else {
            intent.getParcelableExtra(Constants.contact)
        }
    }

    private fun createContactsList(name: String?, phone: String?, description: String?) {
        contactsList.add(Contact(name?:"",phone?:"", description?:"",contactObj?.image ?:R.drawable.avatar))
    }

    private fun initRecyclerView() {
        if (contactObj != null) {
            createContactsList(contactObj?.name, contactObj?.phone, contactObj?.description)
        }

        adapter = ContactsRecyclerAdapter(this.contactsList)
        binding.rvContactsItemsList.adapter = adapter
        adapter.notifyItemInserted(contactsList.size-1)
        adapter.onContactClickListener =ContactsRecyclerAdapter.OnContactClickListener {
                contact,position ->  navigateToContactDetailsActivity(contact)
        }
        }

    private fun navigateToContactDetailsActivity(contact: Contact) {
        val intent = Intent(this,ContactDetailsActivity::class.java)
        intent.putExtra(Constants.contactDetails,contact)
        startActivity(intent)
    }



    }

