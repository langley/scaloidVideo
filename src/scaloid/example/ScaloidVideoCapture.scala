package scaloid.example

import org.scaloid.common._
import android.hardware.Camera
import android.media.CamcorderProfile
import android.media.MediaRecorder
import android.view.Gravity
import android.view.View

class ScaloidVideoCapture extends SActivity {

  var startRecordingButton: SImageButton = null
  var stopRecordingButton:  SImageButton = null
  
  onCreate {
    val camera = Camera.open
    contentView =  new SFrameLayout {
        this += new CameraPreview(camera)
        startRecordingButton = SImageButton()
          startRecordingButton.onClick(startRecording).imageResource(0x7f020005).Gravity(Gravity.BOTTOM)
          startRecordingButton.setEnabled(true)
          startRecordingButton.setVisibility(View.VISIBLE)
        stopRecordingButton = SImageButton()
          stopRecordingButton.onClick(stopRecording).imageResource(0x7f020003).Gravity(Gravity.BOTTOM)
          stopRecordingButton.setEnabled(false)
          stopRecordingButton.setVisibility(View.INVISIBLE)
    }
  }
  
  def startRecording(): Unit = { 
    toast("Recording!")
    toggleButtons(false)
  }
  
  def stopRecording(): Unit = { 
    toast("Stopped!")
    toggleButtons(true)
  }

  def toggleButtons(recording: Boolean): Boolean = { 
    val recordingStateNow = !recording
    stopRecordingButton.setVisibility(if (recordingStateNow) View.VISIBLE else View.INVISIBLE)
    stopRecordingButton.setEnabled(recordingStateNow)
    startRecordingButton.setVisibility(if (recordingStateNow) View.INVISIBLE else View.VISIBLE)
    startRecordingButton.setEnabled(!recordingStateNow)
    recordingStateNow
  }
}
