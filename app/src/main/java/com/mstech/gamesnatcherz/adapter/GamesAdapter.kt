package com.mstech.gamesnatcherz.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.activities.SwipeGameActivity
import com.mstech.gamesnatcherz.model.DataItem

class GamesAdapter(
    var context: Context,
    samplelist: List<DataItem?>
) :
    RecyclerView.Adapter<GamesAdapter.MyViewHolder>() {
    private val samplelist: MutableList<DataItem>
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder { // infalte the item Layout
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_list_item, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val m: DataItem = samplelist[position]
        holder.gamename.text = m.title
        Glide.with(context)  //2
            .load(m.imagePath) //3
            .centerCrop() //4
            .placeholder(R.drawable.loading) //5
            .error(R.drawable.ic_launcher_background) //6
            .fallback(R.drawable.ic_launcher_background) //7
            .into(holder.gameimage)
        if (m.finish == 1) {
            holder.gamestatus.text = "You already played this game on " + m.finishedDisplay
            holder.play.visibility =View.GONE
        }else{
            holder.play.visibility =View.VISIBLE
        }
        holder.itemView.setOnClickListener(View.OnClickListener {
            if (m.finish == 1) {
                ToastUtils.showShort("You already played this game...")
            } else {
                context.startActivity(
                    Intent(
                        context,
                        SwipeGameActivity::class.java
                    ).putExtra("gameid", m.gameId.toString())

                )
                (context as Activity).finish()
            }
        })
holder.play.setOnClickListener(View.OnClickListener {
    if (m.finish == 1) {
        ToastUtils.showShort("You already played this game...")
    } else {
        context.startActivity(
            Intent(
                context,
                SwipeGameActivity::class.java
            ).putExtra("gameid", m.gameId.toString())

        )
        (context as Activity).finish()
    }
})
    }

    override fun getItemCount(): Int {
        return samplelist.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var gameimage: ImageView
        var gamename: TextView
        var gamestatus: TextView
        var play :Button

        //
        init {
            // get the reference of item view's
            gameimage = itemView.findViewById<View>(R.id.gameimage) as ImageView
            gamename = itemView.findViewById<View>(R.id.gamename) as TextView
            gamestatus = itemView.findViewById<View>(R.id.gamestatus) as TextView
            play = itemView.findViewById<View>(R.id.play) as Button
        }
    }

    init {
        this.samplelist = samplelist as MutableList<DataItem>
    }
}