package com.mstech.gamesnatcherz.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.activities.SharePrizeActivity
import com.mstech.gamesnatcherz.model.RedeemPrizeList

class RedeemPrizesAdapter(
    var context: Context,
    samplelist: List<RedeemPrizeList?>
) :
    RecyclerView.Adapter<RedeemPrizesAdapter.MyViewHolder>() {
    private val samplelist: MutableList<RedeemPrizeList>
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
        val m: RedeemPrizeList = samplelist[position]
        holder.gamename.text=m.prizeText
        holder.prizedescription.text=m.gameConditions
        if (m.shared ==0){
            holder.share.visibility = View.VISIBLE
            holder.shared.visibility =View.GONE
        }else if (m.shared==1){
            holder.share.visibility =View.GONE
            holder.shared.visibility =View.VISIBLE
            holder.shared.text = "Shared From : "+m.sharedFrom
        }
        Glide.with(context)  //2
                    .load(m.prizeImagePath) //3
                    .centerCrop() //4
                    .placeholder(R.drawable.loading) //5
                    .error(R.drawable.ic_launcher_background) //6
                    .fallback(R.drawable.ic_launcher_background) //7
                    .into(holder.gameimage)
        holder.redeemcode.text = "RedeemCode : "+m.redeemCode

        holder.share.setOnClickListener( View.OnClickListener {
            context.startActivity(Intent(context,SharePrizeActivity::class.java).putExtra("cid",m.resultId.toString()).putExtra("type",m.type.toString()))
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
        var redeemcode: TextView
        var shared: TextView
        var share: Button
        //
        init {
            // get the reference of item view's
            gameimage = itemView.findViewById<View>(R.id.prizeimage) as ImageView
            gamename = itemView.findViewById<View>(R.id.prizename) as TextView
            prizedescription = itemView.findViewById<View>(R.id.prizedescription) as TextView
            redeemcode = itemView.findViewById<View>(R.id.redeemcode) as TextView
            shared = itemView.findViewById<View>(R.id.shared) as TextView
            share = itemView.findViewById<View>(R.id.share) as Button
        }
    }

    init {
        this.samplelist = samplelist as MutableList<RedeemPrizeList>
    }
}