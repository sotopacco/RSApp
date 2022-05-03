package com.example.rsapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var runnable: Runnable
    private var handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaplayer=MediaPlayer.create(this,R.raw.song)

        seekbar.progress=0
        seekbar.max=mediaplayer.duration
        play_btn.setOnClickListener {
            if(!mediaplayer.isPlaying){
                mediaplayer.start()
                play_btn.setImageResource(R.drawable.Pause)
            }else{
                mediaplayer.pause()
                play_btn.setImageResource(R.drawable.Play)
            }
        }
        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if(changed){
                    mediaplayer.seekTo(pos)
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        runnable= Runnable {
            seekbar.progress=mediaplayer.currentPosition
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable,1000)

        mediaplayer.setOnCompletionListener {
            play_btn.setImageResource(R.drawable.Play)
            seekbar.progress=0
        }
    }
}
