package com.krystalove.task3

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Bitmap
import kotlinx.android.synthetic.main.activity_photo.*
import android.graphics.BitmapFactory




class PhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        /*var bmp: Bitmap? = null
        val byteArray = intent.getByteArrayExtra("image")
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

        photo_view.setImageBitmap(bmp)*/

    }


}
