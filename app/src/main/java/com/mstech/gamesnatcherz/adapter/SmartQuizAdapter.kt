package com.mstech.gamesnatcherz.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.activities.SmartQuiz
import com.mstech.gamesnatcherz.model.RestaurentHistoryResponse
import java.util.*

class SmartQuizAdapter(
    var context: Context,
    samplelist: List<RestaurentHistoryResponse>
) :
    RecyclerView.Adapter<SmartQuizAdapter.MyViewHolder>() , Filterable {
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
            .error(R.drawable.error) //6
            .fallback(R.drawable.loading) //7
            .into(holder.gameimage)
        holder.itemView.setOnClickListener( View.OnClickListener {
//            context.startActivity(Intent(context,SwipeGameActivity::class.java).putExtra("gameid",m.gameId))
            context. startActivity(
                Intent(context, SmartQuiz::class.java).putExtra(
                    "data",
                    m.businessId.toString()
                )
            )
        })
        holder.itemView.setOnClickListener(View.OnClickListener {
            context. startActivity(
                Intent(context, SmartQuiz::class.java).putExtra(
                    "data",
                    m.businessId.toString()
                )
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
                    val resultList = ArrayList<RestaurentHistoryResponse>()
                    for (row in samplelist) {
                        if (row.businessName?.toLowerCase(Locale.ROOT)!!.contains(charSearch.toLowerCase(
                                Locale.ROOT))) {
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
//        var navigation: Button
        //
        init {
            // get the reference of item view's
            gameimage = itemView.findViewById<View>(R.id.partnerimage) as ImageView
            gamename = itemView.findViewById<View>(R.id.partnername) as TextView
            prizedescription = itemView.findViewById<View>(R.id.partnerdesc) as TextView
//            navigation = itemView.findViewById<View>(R.id.navigate) as Button
//            navigation.text = "Games"
        }
    }

    init {
        this.samplelist = samplelist as MutableList<RestaurentHistoryResponse>
    }
}