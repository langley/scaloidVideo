package scaloid.example

import org.scaloid.common._

import java.io.File;

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
        this += videoView
        backButton = SImageButton()
          backButton.onClick(backButtonClick)
        playButton = SImageButton()
          playButton.onClick(playButtonClick).imageResource(0x7f020005).Gravity(Gravity.BOTTOM)      
        stopButton = SImageButton()
          stopButton.onClick(stopButtonClick).imageResource(0x7f020003).Gravity(Gravity.BOTTOM)
        deleteButton = SImageButton()
          deleteButton.onClick(deleteButtonClick)
    }    
  }
  
  def backButtonClick(): Unit = { 
    toast("backButtonClick")
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