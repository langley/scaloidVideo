package scaloid.example

import org.scaloid.common._
import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;

class CameraPreview(camera: Camera)(implicit context: Context, parentVGroup: TraitViewGroup[_] = null)  extends SSurfaceView()(context, parentVGroup) with SurfaceHolder.Callback {
  val TAG = "CameraPreview"
  super.getHolder().addCallback(this);
  // required for API <= 11
  super.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
  
  def surfaceCreated(holder: SurfaceHolder): Unit = {
    Log.d(TAG, "CameraPreview surface created")
    // now that we have the surface, we can start the preview
    try {
    	camera.setPreviewDisplay(holder)
    	camera.startPreview()
    } catch { 
      case e: IOException => 
    	  Log.wtf(TAG, "Failed to start camera preview", e);
    }    
  }
  
  def surfaceDestroyed(holder: SurfaceHolder): Unit = {
    // we will release the camera preview in our activity before this
    // happens... right? 
    Log.d(TAG, "CameraPreview surface destroyed")
  }
  
  def surfaceChanged(holder: SurfaceHolder, format: Int, w: Int, h: Int): Unit = {
    // our activity runs with screenOrientation="landscape" so we don't
    // care about surface changes
    Log.d(TAG, "CameraPreview surface changed")
  }
} 
