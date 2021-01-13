package com.mstech.gamesnatcherz.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mstech.gamesnatcherz.BusinessItem
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.RecentlyVisitedItem
import com.mstech.gamesnatcherz.activities.ReceiptsActivity
import java.util.*

class RecentVisitAdapter(
    var context: Context,
    samplelist: List<RecentlyVisitedItem>
) :
RecyclerView.Adapter<RecentVisitAdapter.MyViewHolder>() , Filterable {
    private val samplelist: MutableList<RecentlyVisitedItem>
    private var sampleFilterList: MutableList<RecentlyVisitedItem>
    var type:String=""

    init {
        sampleFilterList = samplelist as MutableList<RecentlyVisitedItem>
        this.type = type
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder { // infalte the item Layout
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.receipts_restaurent_item2, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val m: RecentlyVisitedItem = sampleFilterList[position]
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
                    val resultList = ArrayList<RecentlyVisitedItem>()
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
                sampleFilterList = results?.values as MutableList<RecentlyVisitedItem>
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
        this.samplelist = samplelist as MutableList<RecentlyVisitedItem>
    }
}