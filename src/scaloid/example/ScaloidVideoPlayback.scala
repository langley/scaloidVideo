package scaloid.example

import java.io.File;
import org.scaloid.common._

import android.app.Activity
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import android.widget.VideoView
import android.view.Gravity

class ScaloidVideoPlayback extends SActivity {

	val TAG = "VideoPlaybackActivity"
	var videoView: VideoView      = null
  var backButton: SImageButton   = null
  var playButton: SImageButton   = null
  var stopButton: SImageButton   = null
  var deleteButton: SImageButton = null 
  var uri: Uri = null
  
  
  onCreate {
    contentView =  new SFrameLayout {
      videoView = new VideoView(context)
      this += videoView.Gravity(Gravity.CENTER).>>
      backButton = SImageButton().<< (WRAP_CONTENT, WRAP_CONTENT).Gravity(Gravity.LEFT | Gravity.BOTTOM).>>
         backButton.onClick(backButtonClick).imageResource(0x7f020004)
         backButton.setEnabled(true)
         backButton.setVisibility(View.VISIBLE)
      playButton = SImageButton().<< (WRAP_CONTENT, WRAP_CONTENT).Gravity(Gravity.CENTER | Gravity.BOTTOM).>>
         playButton.onClick(playButtonClick).imageResource(0x7f020002)
         playButton.setEnabled(true)
         playButton.setVisibility(View.VISIBLE)
      stopButton = SImageButton().<< (WRAP_CONTENT, WRAP_CONTENT).Gravity(Gravity.CENTER | Gravity.BOTTOM).>>
         stopButton.onClick(stopButtonClick).imageResource(0x7f020003)
      deleteButton = SImageButton().<< (WRAP_CONTENT, WRAP_CONTENT).Gravity(Gravity.RIGHT | Gravity.BOTTOM).>>
         deleteButton.onClick(deleteButtonClick).imageResource(0x7f020000)
         deleteButton.setEnabled(true)
         deleteButton.setVisibility(View.VISIBLE)
    }
   
  }
  
  
  def backButtonClick(): Unit = {
		toast("backButtonClick")
    super.finish
  }
  
  def playButtonClick(): Unit = { 
		  toast("playButtonClick")
		  toggleButtons(true)
  }
  
  def stopButtonClick(): Unit = { 
    toast("stopButtonClick")
    toggleButtons(false)    

  }
  
  def deleteButtonClick(): Unit = { 
    toast("deleteButtonClick")    
  }  
  
  def toggleButtons(playing: Boolean): Boolean = { 
    import Utils._
    enableButton(playing, stopButton)
    enableButton(!playing, playButton)
    !playing
  }  
}