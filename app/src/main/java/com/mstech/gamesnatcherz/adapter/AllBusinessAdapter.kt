package com.mstech.gamesnatcherz.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mstech.gamesnatcherz.BusinessItem
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.activities.ReceiptsActivity
import java.util.*

class AllBusinessAdapter(
    var context: Context,
    samplelist: List<BusinessItem>
) :
    RecyclerView.Adapter<AllBusinessAdapter.MyViewHolder>() , Filterable {
    private val samplelist: MutableList<BusinessItem>
    private var sampleFilterList: MutableList<BusinessItem>
    var type:String=""

    init {
        sampleFilterList = samplelist as MutableList<BusinessItem>
        this.type = type
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder { // infalte the item Layout
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.receipts_restaurent_item, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val m: BusinessItem = sampleFilterList[position]
        holder.gamename.text=m.businessName
        Glide.with(context)  //2
            .load(m.businessLogoPath) //3
            .fitCenter() //4
            .placeholder(R.drawable.loading) //5
            .error(R.drawable.error) //6
            .fallback(R.drawable.loading) //7
            .into(holder.gameimage)
        holder.itemView.setOnClickListener(View.OnClickListener {
            context. startActivity(
                Intent(
                    context,
                    ReceiptsActivity::class.java
                ).putExtra("data",m.businessId.toString())
            )
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
                    val resultList = ArrayList<BusinessItem>()
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
                sampleFilterList = results?.values as MutableList<BusinessItem>
                notifyDataSetChanged()
            }

        }
    }
    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var gameimage: ImageView
        var gamename: TextView
        //
        init {
            // get the reference of item view's
            gameimage = itemView.findViewById<View>(R.id.image) as ImageView
            gamename = itemView.findViewById<View>(R.id.name) as TextView
        }
    }

    init {
        this.samplelist = samplelist as MutableList<BusinessItem>
    }

}