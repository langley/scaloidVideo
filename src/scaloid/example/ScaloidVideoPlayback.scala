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
  var backButton: ImageButton   = null
  var playButton: ImageButton   = null
  var stopButton: ImageButton   = null
  var deleteButton: ImageButton = null 
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
  }
  
  def stopButtonClick(): Unit = { 
    toast("stopButtonClick")    
  }
  
  def deleteButtonClick(): Unit = { 
    toast("deleteButtonClick")    
  }  
}