package com.example.videoamademande;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Lien vers la documentation de MX Video Player ==> https://sites.google.com/site/mxvpen/api
    public static final String MXVP = "com.mxtech.videoplayer.ad";

    public static final String EXTRA_VIDEO_LIST = "video_list";
    public static final String EXTRA_POSITION = "position";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchVideo(View v) {
        try{
            // Chemin de la vidéo
            Uri videoUri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Movies/chrono.mp4");

            // Nouvelle activité qui permet de visionner une donnée (dans notre cas, la vidéo)
            Intent intent = new Intent(Intent.ACTION_VIEW);

            // On spécifie la donnée de l'activité ainsi que le MIME (ex: application, text, image, audio, etc.)
            // On veut lancer l'APPLICATION MX Video Player donc le MIME est "application"
            intent.setDataAndType(videoUri, "application/x-mpegURL");

            intent.putExtra(EXTRA_VIDEO_LIST, new Parcelable[] {videoUri});    // Permet d'éviter de lire toutes les vidéos du dossier du chemin envoyé
            intent.putExtra(EXTRA_POSITION, 10000);    // Commence la vidéo à 10sec

            //intent.setPackage(MXVP);    // Limite les applications possibles à celle de MX Video Player
            startActivity(intent);
        }
        catch( ActivityNotFoundException e2){
            displayToast(getResources().getString(R.string.error_unknownMX)); // Erreur, on affiche un message à l'utilisateur
            Log.e( "Error", getResources().getString(R.string.error_unknownMX));
        }
    }

    protected void displayToast(String message){
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }
}