package scaloid.example

import org.scaloid.common._
import android.view.View

object Utils {
  
	def enableButton(on: Boolean, button: SImageButton) = { 
		val visiblity = if (on) View.VISIBLE else View.INVISIBLE
				button.setVisibility(visiblity)
				button.setEnabled(on)
	}
    
}