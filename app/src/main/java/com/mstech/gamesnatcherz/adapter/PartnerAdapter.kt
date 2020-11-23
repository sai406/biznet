package com.mstech.gamesnatcherz.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.activities.GamesListActivity
import com.mstech.gamesnatcherz.model.PartnerResponse

class PartnerAdapter(
    var context: Context,
    samplelist: List<PartnerResponse>
) :
    RecyclerView.Adapter<PartnerAdapter.MyViewHolder>() {
    private val samplelist: MutableList<PartnerResponse>
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder { // infalte the item Layout
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.redeem_list, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val m: PartnerResponse = samplelist[position]
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
                Intent(context, GamesListActivity::class.java).putExtra(
                    "data",
                   m.businessId.toString()
                )
            )
        })

    }

    override fun getItemCount(): Int {
        return samplelist.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var gameimage: ImageView
        var gamename: TextView
        var prizedescription: TextView
        //
        init {
            // get the reference of item view's
            gameimage = itemView.findViewById<View>(R.id.prizeimage) as ImageView
            gamename = itemView.findViewById<View>(R.id.prizename) as TextView
            prizedescription = itemView.findViewById<View>(R.id.prizedescription) as TextView
        }
    }

    init {
        this.samplelist = samplelist as MutableList<PartnerResponse>
    }
}