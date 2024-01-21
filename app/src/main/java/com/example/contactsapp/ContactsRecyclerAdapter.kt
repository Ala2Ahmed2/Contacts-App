package com.example.contactsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

 class ContactsRecyclerAdapter (val contacts:List<Contact>)
    :RecyclerView.Adapter<ContactsRecyclerAdapter.MyViewHolder>() {
     class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val image: ImageView = itemView.findViewById(R.id.imv_contact_item)
         val name: TextView = itemView.findViewById(R.id.tv_contact_item_name)
         val phone: TextView = itemView.findViewById(R.id.tv_contact_item_Phone)
     fun bind(contact: Contact?){
         image.setImageResource(R.drawable.avatar)
         name.text = contact?.name
         phone.text = contact?.phone
     }
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

         val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
         return MyViewHolder(itemView)
     }

     override fun getItemCount(): Int {
         return contacts.size
     }
     override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         val Contact = contacts.get(position)
         holder.bind(Contact)
         if (onContactClickListener!=null){
             holder.itemView.setOnClickListener{
                 onContactClickListener?.onContactClick(Contact,position)

             }
         }
     }
     var onContactClickListener:OnContactClickListener?=null
     fun interface OnContactClickListener {
         fun onContactClick(contact: Contact ,position: Int)

     }
 }