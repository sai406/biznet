package com.mstech.gamesnatcherz.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.ToastUtils
import com.mstech.gamesnatcherz.Model.SharedKey
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.model.CustomerListDataItem
import org.json.JSONException
import java.util.*


class CustomerListAdapter(
    var context: Context,
    samplelist: List<CustomerListDataItem>,
    result: String?,
    type: String?
) :
    RecyclerView.Adapter<CustomerListAdapter.MyViewHolder>() ,Filterable{
    private val samplelist: MutableList<CustomerListDataItem>
     var sampleFilterList= mutableListOf<CustomerListDataItem>()
     var result = ""
     var type = ""

    init {
//        sampleFilterList = samplelist as MutableList<CustomerListDataItem>
        if (result != null) {
            this.result = result

        }
        if (type != null) {
            this.type = type
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder { // infalte the item Layout
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.customer_list_item, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val m: CustomerListDataItem = sampleFilterList[position]
         holder.name.text = m.firstName+ m.lastName
         holder.mobile.text = m.mobile
         holder.email.text = m.email
        holder.itemView.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(context)
            //set title for alert dialog
            builder.setTitle("Confirmation")
            //set message for alert dialog
            builder.setMessage("Do you want to really share  prize?")
            builder.setIcon(android.R.drawable.ic_menu_info_details)

            //performing positive action
            builder.setPositiveButton("Yes") { dialogInterface, which ->
                sharePrize(m.customerId)
            }
            //performing negative action
            builder.setNegativeButton("No") { dialogInterface, which ->
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(true)
            alertDialog.show()

        })
    }

    private fun sharePrize(customerId: Int?) {
        val url = "http://www.gamesnatcherz.com/api/ShareGamePrize?Resultid="+result+"&Cid="+customerId+"&Sharedby="+SPStaticUtils.getString(SharedKey.CUSTOMER_ID,"")+"&Type="+type
        val requestQueue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                try {
                    if (response.getInt("StatusCode") >= 0) {
                        ToastUtils.showShort("Shared Successfully")
                        ( context as Activity).finish()

                    } else {
                        ToastUtils.showShort(response.getString("StatusMessage"))
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error -> error.printStackTrace() })
        requestQueue?.add(request)

    }

    override fun getItemCount(): Int {
        return sampleFilterList.size
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
//                    sampleFilterList = samplelist
                } else {
                    val resultList = ArrayList<CustomerListDataItem>()
                    for (row in samplelist) {
                        if (row.email!=null){
                            if (row.email.toLowerCase(Locale.ROOT).equals(
                                    charSearch.toLowerCase(
                                        Locale.ROOT
                                    )
                                )||row.mobile?.toString()!!.equals(charSearch.toString())) {
                                resultList.add(row)
                            }
                        }

                    }
                    if (resultList != null) {
                        sampleFilterList = resultList
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = sampleFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                sampleFilterList = results?.values as MutableList<CustomerListDataItem>
                notifyDataSetChanged()
            }

        }
    }
    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var email: TextView
        var mobile: TextView
        //
        init {
            // get the reference of item view's
            name = itemView.findViewById<View>(R.id.name) as TextView
            email = itemView.findViewById<View>(R.id.email) as TextView
            mobile = itemView.findViewById<View>(R.id.mobile) as TextView
        }
    }

    init {
        this.samplelist = samplelist as MutableList<CustomerListDataItem>
    }
}