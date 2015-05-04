package scaloid.example

import org.scaloid.common._

import android.hardware.Camera
import android.media.CamcorderProfile
import android.media.MediaRecorder
import android.view.Gravity
import android.view.View
import android.util.Log
import android.os.Environment
import android.content.Intent
import android.net.Uri

import java.io.File
import java.util.Date
import java.text.SimpleDateFormat

class ScaloidVideoCapture extends SActivity {
  val TAG = "VideoCaptureActivity"
  var startRecordingButton: SImageButton = null
  var stopRecordingButton:  SImageButton = null
  var mediaRecorder: MediaRecorder = null
  var camera: Camera = null
  var cameraPreview: CameraPreview = null
  var file: File = null // TODO look for ways to eliminate some of these
  
  onCreate {
    camera = Camera.open
    contentView =  new SFrameLayout {
        cameraPreview = new CameraPreview(camera)
        this += cameraPreview
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
    camera.unlock()
    mediaRecorder = new MediaRecorder()
    mediaRecorder.setCamera(camera)

    mediaRecorder.setAudioSource(mediaRecorder.AudioSource.CAMCORDER)
    mediaRecorder.setVideoSource(mediaRecorder.VideoSource.CAMERA)
    mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH))
    mediaRecorder.setOutputFile(initFile().getAbsolutePath())
    mediaRecorder.setPreviewDisplay(cameraPreview.getHolder().getSurface())
    try {
    	mediaRecorder.prepare()
    	// start the actual recording
    	// throws IllegalStateException if not prepared
    	mediaRecorder.start()
    	// enable the stop button by indicating that we are recording
    	toggleButtons(true)
    } catch { 
      case e: Exception => 
      	Log.wtf(TAG, "Failed to prepare MediaRecorder", e)
        toast(s"startRecording failed ${e.getCause}")
      	releaseMediaRecorder()
    }    

    toast("Recording!")
    toggleButtons(false)
  }
  
  def stopRecording(): Unit = { 
    toast("Stopped!")
        try {
            mediaRecorder.stop()
            toast("recording saved")
            // we are no longer recording
            toggleButtons(false)
        } catch { 
          case e: RuntimeException => 
            // the recording did not succeed
            Log.w(TAG, "Failed to record", e)
            if (file != null && file.exists() && file.delete()) {
                Log.d(TAG, "Deleted " + file.getAbsolutePath())
            }
            return
        } finally {
            this.releaseMediaRecorder()
        }
        if (this.file == null || !this.file.exists()) {
            Log.w(TAG, "File does not exist after stop: " + this.file.getAbsolutePath())
        } else {
            Log.d(TAG, "Going to display the video: " + this.file.getAbsolutePath())
             val intent: Intent = new Intent(this, classOf[scaloid.example.ScaloidVideoPlayback])
             //intent.setData(Uri.fromFile(file))
             startActivity(intent)
        }    
    toggleButtons(true)
  }

  def releaseMediaRecorder(): Unit = {
    if (mediaRecorder != null) {
        mediaRecorder.reset() // clear configuration (optional here)
        mediaRecorder.release()
        mediaRecorder = null
    }    
  }
  
  def toggleButtons(recording: Boolean): Boolean = { 
    import Utils._
    enableButton(!recording, stopRecordingButton)
    enableButton(recording, startRecordingButton)
    !recording
  }
  
  def initFile(): File = {
    var file: File = null
    val dir: File = new File(
              Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), this.getClass().getPackage().getName())
    if (!dir.exists() && !dir.mkdirs()) {
    	Log.wtf(TAG, "Failed to create storage directory: " + dir.getAbsolutePath())
    	toast("initFile failed")
    	file = null
    } else {
    	file = new File(dir.getAbsolutePath(), new SimpleDateFormat("'IMG_'yyyyMMddHHmmss'.m4v'").format(new Date()))
    }
    this.file = file
    file
  }
  
}
