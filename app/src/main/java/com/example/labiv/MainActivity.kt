package com.example.labiv

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {


    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val imgViewMain = findViewById<ImageView>(R.id.imgViewMain)
        val btnAnimImage = findViewById<Button>(R.id.btnAnimImage)

        btnAnimImage.setOnClickListener {
            // ya folla LG
            val animation = AnimationUtils.loadAnimation(this, R.anim.fade_rotate)
            imgViewMain.startAnimation(animation)
        }

        val btnPlayAudio = findViewById<Button>(R.id.btnPlayAudio)
        val btnPauseAudio = findViewById<Button>(R.id.btnPauseAudio)


        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.audio)
        } catch (_: Exception) {
            Toast.makeText(this, "Error: Falta audio.mp3 en res/raw", Toast.LENGTH_LONG).show()
        }

        btnPlayAudio.setOnClickListener {
            if (mediaPlayer?.isPlaying == false) {
                mediaPlayer?.start()
                Toast.makeText(this, "Reproduciendo Audio", Toast.LENGTH_SHORT).show()
            }
        }

        btnPauseAudio.setOnClickListener {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
                Toast.makeText(this, "Audio Pausado", Toast.LENGTH_SHORT).show()
            }
        }
        val videoView = findViewById<VideoView>(R.id.videoViewMain)
        val btnPlayVideo = findViewById<Button>(R.id.btnPlayVideo)

        val videoPath = "android.resource://$packageName/${R.raw.video}"
        val uri = videoPath.toUri()
        videoView.setVideoURI(uri)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        btnPlayVideo.setOnClickListener {
            videoView.start()
            Toast.makeText(this, "Reproduciendo Video", Toast.LENGTH_SHORT).show()
        }

        val imgIconAnim = findViewById<ImageView>(R.id.imgIconAnim)
        val btnStartIconAnim = findViewById<Button>(R.id.btnStartIconAnim)

        btnStartIconAnim.setOnClickListener {

            imgIconAnim.animate().apply {
                duration = 1000
                rotationBy(360f) // Girar 360 grados
                scaleX(1.5f)     // Crecer en X
                scaleY(1.5f)     // Crecer en Y
            }.withEndAction {

                imgIconAnim.animate().scaleX(1f).scaleY(1f).duration = 500
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mediaPlayer?.release()
        mediaPlayer = null
    }
}