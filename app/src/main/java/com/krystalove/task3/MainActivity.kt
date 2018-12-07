package com.krystalove.task3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.content.Intent
import android.os.Environment
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import android.support.v4.content.FileProvider
import android.app.Activity
import android.support.design.widget.Snackbar

class MainActivity : AppCompatActivity() {

    private val REQUEST_CAPTURE_IMAGE = 200
    private val IMAGE_LOCATION_TAG = "IMAGE"
    private val NOTE_TAG = "NOTE"
    private var image_file_location = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        camera_btn.setOnClickListener{
            if(!note_text.text.isEmpty()) openCameraIntent()
            else showWarning(R.string.warning_message_1)
        }
    }

    private fun openCameraIntent() {
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (pictureIntent.resolveActivity(packageManager) != null) {
            var photoFile: File? = null
            try { photoFile = createImageFile() }
            catch (ex: IOException) { showWarning(R.string.error_message_1) }

            if (photoFile != null) {
                val authorities = BuildConfig.APPLICATION_ID + ".fileprovider"
                val photoURI = FileProvider.getUriForFile(this, authorities, photoFile)

                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(pictureIntent, REQUEST_CAPTURE_IMAGE)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        //val image = File.createTempFile("image", ".jpg", storageDir)
        val image = File(storageDir?.path+"/image.jpg")
        image_file_location = image.absolutePath
        return image
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode == REQUEST_CAPTURE_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                val photoActivityIntent = Intent(this, PhotoActivity::class.java)
                photoActivityIntent.putExtra(IMAGE_LOCATION_TAG,image_file_location)
                photoActivityIntent.putExtra(NOTE_TAG,note_text.text.toString())
                startActivity(photoActivityIntent)
                finish()
            }
            else showWarning(R.string.error_message_2)
        }
        super.onActivityResult(requestCode, resultCode, intent)
    }
    private fun showWarning(message_id:Int){
        Snackbar.make(root_layout, message_id, Snackbar.LENGTH_LONG).show()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        image_file_location = savedInstanceState?.getString(IMAGE_LOCATION_TAG)!!
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(IMAGE_LOCATION_TAG, image_file_location)
    }

}
