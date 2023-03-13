package com.churchinwales.prayer

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.IOException
import java.io.InputStream

class ItemAdapter(private val context: Context, private val dataset: List<Card>)
    : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val cardImage : ImageView = view.findViewById(R.id.cardImage)
        val cardText : TextView = view.findViewById(R.id.cardText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
    /*
        val fileString = context.assets.open( "/images/"+item.cardImage)
        AppDebug.log("ImageURI", fileString)
        val uri = Uri.fromFile( File(fileString))
*/
        val file : String = item.cardImage
        val bitmap: Bitmap? =  context.assetsToBitmap(file)

        bitmap?.apply {
            holder.cardImage.setImageBitmap(this)
        }

        holder.cardText.text = Html.fromHtml(item.cardText,Html.FROM_HTML_MODE_COMPACT)
    }

    override fun getItemCount() = dataset.size

}

fun Context.assetsToBitmap(fileName: String): Bitmap? {

    return try {
        with(assets.open(fileName)){
            BitmapFactory.decodeStream(this)
        }
    } catch (e: IOException) {
        AppDebug.log("AssetsToBitmap", "File Not Found "+ fileName)
       null
    }
}