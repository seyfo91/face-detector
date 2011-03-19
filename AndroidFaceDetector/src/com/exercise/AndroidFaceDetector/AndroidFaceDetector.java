package com.exercise.AndroidFaceDetector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;


public class AndroidFaceDetector extends Activity {
    
	public static final String TAG = "Android Face Detector";
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
			setContentView(new myView(this));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "FileNotFoundException", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "IOException", e);
		}
    }
    
    
    private class myView extends View{
        
        private int imageWidth, imageHeight, imageWidth1, imageHeight1;
        private int numberOfFace = 1;
        private FaceDetector myFaceDetect, myFaceDetect1; 
        private FaceDetector.Face[] myFace, myFace1;
        private static final int alert_code = 1;
        float myEyesDistance;
        int numberOfFaceDetected, numberOfFaceDetected1; 
        Bitmap myBitmap;
		int maxLength = 450;
		     
    public myView(Context context) throws FileNotFoundException, IOException {
    	 super(context);
    	 
    	 // TODO Auto-generated constructor stub
	 		// BitmapFactory.Options BitmapFactoryOptionsbfo = new BitmapFactory.Options();
	 		// BitmapFactoryOptionsbfo.inPreferredConfig = Bitmap.Config.RGB_565; 
      
      /*String strAvatarPrompt = "Take your picture to store as your avatar!";
      
      try {
    	  Intent pictureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    	  startActivityForResult(Intent.createChooser(pictureIntent, strAvatarPrompt), TAKE_AVATAR_CAMERA_REQUEST);
    	  //myBitmap = (Bitmap) pictureIntent.getExtras().get("data");
      } 
      catch (Exception e) {
          Log.e(TAG, "Intent pictureIntent Call failed.", e);
      }*/
      
     
      /*protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {

    	 //BitmapFactory.Options BitmapFactoryOptionsbfo = new BitmapFactory.Options();
         //BitmapFactoryOptionsbfo.inPreferredConfig = Bitmap.Config.RGB_565; 
    	 
         switch (requestCode) {
         case TAKE_AVATAR_CAMERA_REQUEST:

             if (resultCode == Activity.RESULT_CANCELED) {
                 // Avatar camera mode was canceled.
            	 Log.i(TAG, "onActivityResult: Avatar camera mode was canceled.");
             } else if (resultCode == Activity.RESULT_OK) {

            	// Toast.makeText(this, УTODO: Launch DatePickerDialogФ, Toast.LENGTH_LONG).show();
            	 
                 // Took a picture, use the downsized camera image provided by default
                 myBitmap = (Bitmap) data.getExtras().get("data");
                 Log.i(TAG, "onActivityResult Call is OK."); 
             }
             break;
         }*/
    	 		 startActivityForResult(new Intent(AndroidFaceDetector.this, Cam.class), 1);
    	 		 //startActivity(new Intent(AndroidFaceDetector.this, Cam.class));
    	 
    	 		 
 	    		 Uri photoUri = Uri.fromFile(new File("/sdcard/CameraExample/", "photo.jpg"));
 	    	     BitmapFactory.Options BitmapFactoryOptionsbfo = new BitmapFactory.Options();
    	 		 BitmapFactoryOptionsbfo.inPreferredConfig = Bitmap.Config.RGB_565;
    	 		 myBitmap = Media.getBitmap(getContentResolver(), photoUri);
    	 		 myFace = new FaceDetector.Face[numberOfFace];
    	 	     imageWidth = myBitmap.getWidth();
                 imageHeight = myBitmap.getHeight();
    	 		 myFaceDetect = new FaceDetector(imageWidth, imageHeight, numberOfFace);
                 numberOfFaceDetected = myFaceDetect.findFaces(myBitmap, myFace); 
    	 		 Bitmap ScaledMyBitmap = createScaledBitmapKeepingAspectRatio(myBitmap, maxLength); 
    	 		 myBitmap = ScaledMyBitmap;
                 Bitmap myBitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.katrin2, BitmapFactoryOptionsbfo);     
                 imageWidth1 = myBitmap1.getWidth();
                 imageHeight1 = myBitmap1.getHeight();
                 myFace1 = new FaceDetector.Face[numberOfFace];
                 myFaceDetect1 = new FaceDetector(imageWidth1, imageHeight1, numberOfFace);
                 numberOfFaceDetected1 = myFaceDetect1.findFaces(myBitmap1, myFace1); 
                 
                 Face face = myFace1[numberOfFaceDetected1-1];
                 myEyesDistance = face.eyesDistance();
                 
           	     float v_EULER_X, v_EULER_Y, v_EULER_Z;
                 PointF v_PointF = new PointF(0,0);
           	     face.getMidPoint(v_PointF);
                 v_EULER_X = face.pose(Face.EULER_X);
                 v_EULER_Y = face.pose(Face.EULER_Y);
                 v_EULER_Z = face.pose(Face.EULER_Z);
                 
                 final String sss = String.valueOf (myEyesDistance);
                 Log.d(TAG, "myView() myEyesDistance: "+sss);
                 final String sss1 = String.valueOf (v_PointF.x);
                 Log.d(TAG, "myView() v_PointF.x:"+sss1);
                 final String sss2 = String.valueOf (v_PointF.y);
                 Log.d(TAG, "myView() v_PointF.y:"+sss2);
                 final String sss3 = String.valueOf (v_EULER_X);
                 Log.d(TAG, "myView() v_EULER_X:"+sss3);
                 final String sss4 = String.valueOf (v_EULER_Y);
                 Log.d(TAG, "myView() v_EULER_Y:"+sss4);
                 final String sss5 = String.valueOf (v_EULER_Z);
                 Log.d(TAG, "myView() v_EULER_Z:"+sss5);
             }
        // break;
             
         //}
     //}
     
    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) throws FileNotFoundException, IOException {
    	if (requestCode == 1){
    	if (resultCode == Activity.RESULT_CANCELED) {
            // Avatar camera mode was canceled.
        } else if (resultCode == Activity.RESULT_OK) {
        	
        	 Uri photoUri = Uri.fromFile(new File("/sdcard/CameraExample/", "photo.jpg"));
	 		 BitmapFactory.Options BitmapFactoryOptionsbfo = new BitmapFactory.Options();
	 		 BitmapFactoryOptionsbfo.inPreferredConfig = Bitmap.Config.RGB_565;
	 		 myBitmap = Media.getBitmap(getContentResolver(), photoUri);
	 		 myFace = new FaceDetector.Face[numberOfFace];
	 	     imageWidth = myBitmap.getWidth();
             imageHeight = myBitmap.getHeight();
	 		 myFaceDetect = new FaceDetector(imageWidth, imageHeight, numberOfFace);
             numberOfFaceDetected = myFaceDetect.findFaces(myBitmap, myFace); 
	 		 Bitmap ScaledMyBitmap = createScaledBitmapKeepingAspectRatio(myBitmap, maxLength); 
	 		 myBitmap = ScaledMyBitmap;
        }
    }
    }*/
    
    private Bitmap createScaledBitmapKeepingAspectRatio(Bitmap bitmap, int maxSide) {
        int orgHeight = bitmap.getHeight();
        int orgWidth = bitmap.getWidth();

        // scale to no longer any either side than 75px
        int scaledWidth = (orgWidth >= orgHeight) ? maxSide : (int) ((float) maxSide * ((float) orgWidth / (float) orgHeight));
        int scaledHeight = (orgHeight >= orgWidth) ? maxSide : (int) ((float) maxSide * ((float) orgHeight / (float) orgWidth));

        // create the scaled bitmap
        Bitmap scaledGalleryPic = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
        return scaledGalleryPic;
    }
 
    
     @Override
    protected void onDraw(Canvas canvas) {
      // TODO Auto-generated method stub
      
               canvas.drawBitmap(myBitmap, 0, 0, null);
               
               Paint myPaint = new Paint();
               myPaint.setColor(Color.GREEN);
               myPaint.setStyle(Paint.Style.STROKE); 
               myPaint.setStrokeWidth(3);
               if (numberOfFaceDetected == 0)
               {
            	   showDialog(alert_code);
            	   return;
            	   // «десь надо вставить вывод сообщени€ о том, что распознано или о том что ничего не распознано. 
               }
 
               for(int i=0; i < numberOfFaceDetected; i++)
               {
                Face face = myFace[i];
                PointF myMidPoint = new PointF();
                face.getMidPoint(myMidPoint);
                canvas.drawRect(
                  (int)(myMidPoint.x - myEyesDistance),
                  (int)(myMidPoint.y - myEyesDistance),
                  (int)(myMidPoint.x + myEyesDistance),
                  (int)(myMidPoint.y + myEyesDistance),
                  myPaint);
               }
               //finish();
     }
       }
    
    protected Dialog onCreateDialog(int id)
    {
    			DialogInterface.OnClickListener doNothing = new DialogInterface.OnClickListener()
    			{
    				public void onClick(DialogInterface dialog, int whichButton)
    				{
    				}
    			};
    int alertMessage;
    alertMessage = R.string.alert;
    return new AlertDialog.Builder(this)
    		.setMessage(alertMessage)
    		.setNeutralButton(R.string.ok, doNothing)
    		.create();
    } 
   }
