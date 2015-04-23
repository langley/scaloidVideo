package scaloid.example

import org.scaloid.common._
import android.content.Intent
import android.hardware.Camera
import android.media.CamcorderProfile
import android.media.MediaRecorder
import android.view.Gravity

class ScaloidVideoCapture extends SActivity {

//  onCreate {
//    contentView = new SVerticalLayout {
//      style {
//        case b: SButton => b.textColor(Color.RED).onClick(toast("Bang!"))
//        case t: STextView => t textSize 10.dip
//        case e: SEditText => e.backgroundColor(Color.YELLOW).textColor(Color.BLACK)
//      }
//      STextView("I am 10 dip tall... whatever that means")
//      STextView("Me too")
//      STextView("I am 15 dip tall") textSize 15.dip // overriding
//      this += new SLinearLayout {
//        STextView("Button: ")
//        SButton(R.string.red)
//      }.wrap
//      SEditText("ScaloidVideo is Yellow!").fill
//    } padding 20.dip
//  }
  var recording: Boolean = false;
  onCreate { 
    val camera = Camera.open
    contentView =  new SFrameLayout {
        SImageButton().onClick(startRecording).imageResource(0x7f020005).Gravity(Gravity.TOP)// .alignParentTop
        this += new CameraPreview(camera)
        SImageButton().onClick(stopRecording).imageResource(0x7f020003).Gravity(Gravity.BOTTOM) //.alignParentBottom
    }
  }
  
  def startRecording(): Unit = { 
    toast("Recording!")
  }
  
  def stopRecording(): Unit = { 
    toast("Stopped!")
  }

}
