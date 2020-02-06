package com.steveberek.cordova.plugin;

import android.app.Dialog;
import android.view.Window;
import android.view.WindowManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.Uri;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import java.io.File;
import java.util.ArrayList;


public class PhenixVideoPlayer extends CordovaPlugin {

     private PlayerView playerView1;
     private SimpleExoPlayer player1;
     private String TAG = "VIDEOPLAYER_PRUEBAS";
     private ArrayList<Uri> videosUris = new ArrayList<>() ;
     private ConcatenatingMediaSource concatenatingMediaSource;
     private DataSource.Factory dataSourceFactory;
     private int playIndex = 0;
     private boolean loopMode = false;
     private FrameLayout rootView;
     private Dialog dialog;
     private String videoFiles;

  @Override
  public boolean execute(String action, JSONArray args,
    final CallbackContext callbackContext) {
      // Verify that the user sent a 'show' action
      if (!action.equals("playVideo")) {
        callbackContext.error("\"" + action + "\" is not a recognized action.");
        return false;
      }
    try {
            JSONObject options = args.getJSONObject(0);
            videoFiles = options.getString("videosFiles");
            Log.d(TAG,"RECEIVED_VIDEOS_FILES ->" + videoFiles);
        } catch (JSONException e) {
            callbackContext.error("Error encountered: " + e.getMessage());
            return false;
        }
      try {
             // Create dialog in new thread
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                            createViews();
                            startMultipleVideos();
                }
            });

        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
        callbackContext.sendPluginResult(pluginResult);
      } catch (Exception ex) {
        callbackContext.error("Error encountered: " + ex.getMessage());
        return false;
      }
      return true;
  }

    public void createViews() {
        dialog = new Dialog(cordova.getActivity(), android.R.style.Theme_NoTitleBar);
        dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
       // dialog.setOnDismissListener(this);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rootView = new FrameLayout(cordova.getActivity());
        rootView.setLayoutParams(new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
       /*  rootView.setOrientation(LinearLayout.VERTICAL);
        rootView.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        rootView.setVerticalGravity(Gravity.CENTER_VERTICAL); */

        playerView1 = new SimpleExoPlayerView(cordova.getActivity());
        rootView.addView(playerView1);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        dialog.setContentView(rootView);
        dialog.show();
        dialog.getWindow().setAttributes(lp);
       // setContentView(rootView);
    }
    public void playOneVideo(File file, boolean loop) {
        ArrayList<File> videoFiles = new ArrayList<>();
        videoFiles.add(file);
        initVideoPlaylist(videoFiles, loop);
    }

    public void startOneVideo() {
    File videoFile = new File("//android_asset/video1.mp4");
    playOneVideo(videoFile, true);
    }

    public void startMultipleVideos() {
        ArrayList<File> videoFiles = new ArrayList<>();
    videoFiles.add(new File("//android_asset/video1.mp4"));
    videoFiles.add(new File("//android_asset/video2.mp4"));
    videoFiles.add(new File("//android_asset/video3.mp4"));
    initVideoPlaylist(videoFiles, true);
    }


    public void initDataSourceFactory () {
         dataSourceFactory = new DefaultDataSourceFactory(cordova.getActivity(),
                Util.getUserAgent(cordova.getActivity(), "yourApplicationName"));
    }

    public void initVideoPlaylist(ArrayList<File> videoFiles, boolean loop) {
        this.loopMode = loop;
        if ( videoFiles != null && videoFiles.size() > 0 ) {
            videosUris = new ArrayList<>();
            for ( File file: videoFiles ) {
                videosUris.add(Uri.fromFile(file));
            }

            initializePlayer();
            initDataSourceFactory();
            prepareMediaSources();
            runVideoPlaylist();

        } else {
            Log.d(TAG, "VIDEO_FILES_LIST_CANNOT_BE_EMPTY");
        }
    }

    public void prepareMediaSources() {
      concatenatingMediaSource = new ConcatenatingMediaSource();
      for ( Uri videoUri : videosUris ){
          MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                  .createMediaSource(videoUri);
          concatenatingMediaSource.addMediaSource(mediaSource);
      }
    }

    public void runVideoPlaylist() {
        player1.prepare(concatenatingMediaSource);
        player1.setPlayWhenReady(true);
    }

    public void initializePlayer() {

         player1 = new SimpleExoPlayer.Builder(cordova.getActivity()).build();
         playerView1.setPlayer(player1);

         player1.addListener(new Player.EventListener() {
             @Override
             public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                 switch (playbackState) {
                     case Player.STATE_READY :
                         Log.d(TAG,"STATE -> READY");
                         break;
                     case Player.STATE_ENDED :
                         Log.d(TAG,"STATE -> ENDED");
                         if ( loopMode ) {
                             runVideoPlaylist();
                         }
                         break;
                     case Player.STATE_BUFFERING :
                         Log.d(TAG,"STATE -> BUFFERING");
                         break;
                     case Player.STATE_IDLE :
                         Log.d(TAG,"STATE -> IDLE");
                         break;
                 }
             }
         });

    }
}