package scaloid.example

import org.scaloid.common._
import android.hardware.Camera
import android.media.CamcorderProfile
import android.media.MediaRecorder
import android.view.Gravity
import android.view.View
import android.util.Log
import android.os.Environment

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
    mediaRecorder = new MediaRecorder();
    mediaRecorder.setCamera(camera);

    mediaRecorder.setAudioSource(mediaRecorder.AudioSource.CAMCORDER);
    mediaRecorder.setVideoSource(mediaRecorder.VideoSource.CAMERA);
    mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
    mediaRecorder.setOutputFile(initFile().getAbsolutePath());
    mediaRecorder.setPreviewDisplay(cameraPreview.getHolder().getSurface());
    try {
    	mediaRecorder.prepare();
    	// start the actual recording
    	// throws IllegalStateException if not prepared
    	mediaRecorder.start();
    	// enable the stop button by indicating that we are recording
    	toggleButtons(true);
    } catch { 
      case e: Exception => 
      	Log.wtf(TAG, "Failed to prepare MediaRecorder", e);
        toast(s"startRecording failed ${e.getCause}")
      	releaseMediaRecorder();
    }    

    toast("Recording!")
    toggleButtons(false)
  }
  
  def stopRecording(): Unit = { 
    toast("Stopped!")
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
    def enableButton(on: Boolean, button: SImageButton) = { 
      val visiblity = if (on) View.VISIBLE else View.INVISIBLE
      button.setVisibility(visiblity)
      button.setEnabled(on)
    }
    enableButton(!recording, stopRecordingButton)
    enableButton(recording, startRecordingButton)
    !recording
  }
  
  def initFile(): File = {
    var file: File = null
        val dir: File = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), this
                        .getClass().getPackage().getName());
        if (!dir.exists() && !dir.mkdirs()) {
            Log.wtf(TAG, "Failed to create storage directory: " + dir.getAbsolutePath());
            toast("initFile failed")
            file = null;
        } else {
            file = new File(dir.getAbsolutePath(), new SimpleDateFormat(
                    "'IMG_'yyyyMMddHHmmss'.m4v'").format(new Date()));
        }
        file
    }
  
}
