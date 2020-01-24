package implement.dfw.teleprompt.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.wonderkiln.camerakit.CameraKit;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import implement.dfw.teleprompt.R;

public class Teleprompter extends AppCompatActivity {

    public static final String CAMPERA_LOG = "Campera_Log";
    private CameraView cameraKitView;
    private ScrollView textViewSource;

    private FirebaseStorage storage;             /** Firebase Storage */
    private StorageReference storageRef;
    private InputStream stream;
    private UploadTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_telepromt);

        cameraKitView = findViewById(R.id.camera);
    }


    @Override
    protected void onStart() {

        Log.i(CAMPERA_LOG, "Teleprompt onStart()");
        super.onStart();
        cameraKitView.setFocus(CameraKit.Constants.FOCUS_TAP);
        cameraKitView.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        storage = FirebaseStorage.getInstance();

        cameraKitView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {

            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

                // Create a storage reference from our app
                storageRef = storage.getReference();
                StorageReference mountainsRef = storageRef.child("streaming.mp4");
                if (cameraKitVideo != null){
                    try {
                        stream = new FileInputStream(new File(String.valueOf(cameraKitVideo.getVideoFile())));
                        uploadTask = mountainsRef.putStream(stream);
                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            }
                        });

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

        Log.i(CAMPERA_LOG, "Teleprompt onResume()");
    }

    @Override
    protected void onPause() {
        cameraKitView.stop();
        Log.i(CAMPERA_LOG, "Teleprompt onPause()");

        super.onPause();
    }

    @Override
    protected void onStop() {
        if(cameraKitView != null){
            cameraKitView.stop();

        }
        Log.i(CAMPERA_LOG, "Teleprompt onStop()");

        super.onStop();
    }



    /**
     * Reference link to implement that older version of CameraKit (v0.13.4)
     * https://www.diycode.cc/projects/wonderkiln/CameraKit-Android
     *
     * @param view
     */

    public void captureMotion(View view) {

        /** Works without any file input */
        cameraKitView.captureVideo();

    }

    public void stopRecording(View view) {
        cameraKitView.stopVideo();

    }
}
