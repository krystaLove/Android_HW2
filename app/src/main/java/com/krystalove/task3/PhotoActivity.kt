package com.krystalove.task3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_photo.*
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class PhotoActivity : AppCompatActivity() {

    private val IMAGE_LOCATION_TAG = "IMAGE"
    private val NOTE_TAG = "NOTE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val image_file_location = intent.getStringExtra(IMAGE_LOCATION_TAG)
        println(image_file_location)
        Log.d("imageEE",image_file_location)
        val note_text = intent.getStringExtra(NOTE_TAG)
        val photo = BitmapFactory.decodeFile(image_file_location)

        photo_view.setImageBitmap(photo)
        note.text = note_text.toString()
    }


}
