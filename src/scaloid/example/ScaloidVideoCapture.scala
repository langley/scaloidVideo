package scaloid.example

import org.scaloid.common._
import android.graphics.Color

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
  onCreate { 
    contentView =  new SVerticalLayout {
      // new SFrameLayout { 
        SImageButton().onClick(startRecording).imageResource(0x7f020005)
        SImageButton().onClick(stopRecording).imageResource(0x7f020003)
        SButton("Yeah", toast("Yeah"))
      //}
    }
  }
  
  def startRecording(): Unit = { 
    toast("Recording!")
  }
  
  def stopRecording(): Unit = { 
    toast("Stopped!")
  }

}
