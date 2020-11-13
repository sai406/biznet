package com.mstech.gamesnatcherz.adapter

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.model.RestaurentHistoryResponse
import java.util.*


class RestaurentHistoryAdapter(
    var context: Context,
    samplelist: List<RestaurentHistoryResponse>
) :
    RecyclerView.Adapter<RestaurentHistoryAdapter.MyViewHolder>() ,Filterable{
    private val samplelist: MutableList<RestaurentHistoryResponse>
    private var sampleFilterList: MutableList<RestaurentHistoryResponse>

    init {
        sampleFilterList = samplelist as MutableList<RestaurentHistoryResponse>
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder { // infalte the item Layout
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.partners_item, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val m: RestaurentHistoryResponse = sampleFilterList[position]
        holder.gamename.text=m.businessName
        holder.prizedescription.text=m.address
        Glide.with(context)  //2
                    .load(m.businessLogoPath) //3
                    .centerCrop() //4
                    .placeholder(R.drawable.loading) //5
                    .error(R.drawable.ic_launcher_background) //6
                    .fallback(R.drawable.ic_launcher_background) //7
                    .into(holder.gameimage)
        holder.navigation.setOnClickListener(View.OnClickListener {
            val uri =
                "http://maps.google.com/maps?daddr=" + m.latitude + "," + m.longitude
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.setPackage("com.google.android.apps.maps")
            try {
               context. startActivity(intent)
            } catch (ex: ActivityNotFoundException) {
                try {
                    val unrestrictedIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                   context. startActivity(unrestrictedIntent)
                } catch (innerEx: ActivityNotFoundException) {
                    Toast.makeText(context, "Please install a maps application", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })

    }

    override fun getItemCount(): Int {
        return sampleFilterList.size
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    sampleFilterList = samplelist
                } else {
                    val resultList = ArrayList<RestaurentHistoryResponse>()
                    for (row in samplelist) {
                        if (row.businessName?.toLowerCase(Locale.ROOT)!!.contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )
                            )) {
                            resultList.add(row)
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
                sampleFilterList = results?.values as MutableList<RestaurentHistoryResponse>
                notifyDataSetChanged()
            }

        }
    }
    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var gameimage: ImageView
        var gamename: TextView
        var prizedescription: TextView
        var navigation: Button
        //
        init {
            // get the reference of item view's
            gameimage = itemView.findViewById<View>(R.id.partnerimage) as ImageView
            gamename = itemView.findViewById<View>(R.id.partnername) as TextView
            prizedescription = itemView.findViewById<View>(R.id.partnerdesc) as TextView
            navigation = itemView.findViewById<View>(R.id.navigate) as Button
        }
    }

    init {
        this.samplelist = samplelist as MutableList<RestaurentHistoryResponse>
    }
}