package com.krystalove.task3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_photo.*
import android.graphics.BitmapFactory

class PhotoActivity : AppCompatActivity() {

    companion object {
        const val IMAGE_LOCATION_TAG = "IMAGE"
        const val NOTE_TAG = "NOTE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val image_file_location = intent.getStringExtra(IMAGE_LOCATION_TAG)
        val note_text = intent.getStringExtra(NOTE_TAG)

        val photo = BitmapFactory.decodeFile(image_file_location)

        photo_view.setImageBitmap(photo)
        note.text = note_text.toString()
    }


}
