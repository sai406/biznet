package com.mstech.gamesnatcherz.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.mstech.gamesnatcherz.Model.SharedKey
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.activities.PartnerDetailsActicity
import com.mstech.gamesnatcherz.model.RestaurentHistoryResponse
import org.json.JSONException
import java.util.*


class RestaurentHistoryAdapter(
    var context: Context,
    samplelist: List<RestaurentHistoryResponse>,
    type: String
) :
    RecyclerView.Adapter<RestaurentHistoryAdapter.MyViewHolder>() ,Filterable{
    private val samplelist: MutableList<RestaurentHistoryResponse>
    private var sampleFilterList: MutableList<RestaurentHistoryResponse>
     var type:String=""

    init {
        sampleFilterList = samplelist as MutableList<RestaurentHistoryResponse>
        this.type = type
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
        if (m.isfavorite.equals("0")){
            holder.favorite.setBackgroundDrawable(context.resources.getDrawable(R.drawable.ic_favorite_unselected))
        }else{
            holder.favorite.setBackgroundDrawable(context.resources.getDrawable(R.drawable.ic_favorite_selected))
        }
        holder.favorite.setOnClickListener(View.OnClickListener {
            favoriteAction(m.businessId,SPStaticUtils.getString(SharedKey.CUSTOMER_ID,"0"),position,holder)
        })
        Glide.with(context)  //2
                    .load(m.businessLogoPath) //3
                    .centerCrop() //4
                    .placeholder(R.drawable.loading) //5
                    .error(R.drawable.error) //6
                    .fallback(R.drawable.loading) //7
                    .into(holder.gameimage)
        holder.navigation.setOnClickListener(View.OnClickListener {
           context. startActivity(
                Intent(
                    context,
                    PartnerDetailsActicity::class.java
                ).putExtra("data", sampleFilterList[position])
            )
        })

    }
    private fun favoriteAction(bid: Int?, customerId: String?, position: Int, holder: MyViewHolder) {
        val url = "http://www.gamesnatcherz.com/api/AddRemoveFavourite?cid="+customerId+"&bid="+bid
        val requestQueue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                try {
                    if (response.getInt("StatusCode") == 0) {
                        if (type.equals("gs")){
                            favoritechange(position,"0")
                        }else{
                            samplelist.removeAt(position)
                            notifyDataSetChanged()
                        }

                        ToastUtils.showShort(response.getString("StatusMessage"))
                    } else {
                        favoritechange(position,"1")
                        ToastUtils.showShort(response.getString("StatusMessage"))
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error -> error.printStackTrace() })
        requestQueue?.add(request)

    }

    private fun favoritechange(position: Int, i: String) {
        samplelist.get(position).isfavorite=i
sampleFilterList.get(position).isfavorite=i
        notifyItemChanged(position)
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
        var favorite: ImageView
        var gamename: TextView
        var prizedescription: TextView
        var navigation: Button
        //
        init {
            // get the reference of item view's
            gameimage = itemView.findViewById<View>(R.id.partnerimage) as ImageView
            favorite = itemView.findViewById<View>(R.id.favorite) as ImageView
            gamename = itemView.findViewById<View>(R.id.partnername) as TextView
            prizedescription = itemView.findViewById<View>(R.id.partnerdesc) as TextView
            navigation = itemView.findViewById<View>(R.id.navigate) as Button
        }
    }

    init {
        this.samplelist = samplelist as MutableList<RestaurentHistoryResponse>
    }
}