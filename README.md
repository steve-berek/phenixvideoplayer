# phenixvideoplayer
The new Cordova Plugin for android video player

PHENIX VIDEO PLAYER FOR ANDROID

THE FOLLOWING FUNCTIONS ARE AVAILABLE

- PLAY A UNIQUE VIDEO
- playVideo(videoPath, loop, controls, fullscreen, mute, successCallback, errorCallback)

videoPath (String) : Path of the file
loop (Boolean) : true if you want the video to be played in loop
controls (Boolean) : true if you want the controls to appear at the bottom of the video
fullscreen ( Boolean ) : true if you want the video to be played in fullscreen (landscape)
successCallback (Callback) : fired when the video ended correctly
errorCallback (Callback) : fired when there is an error


- PLAY A PLAYLIST OF VIDEOS
- playMultipleVideos(videosPaths, loop, controls, fullscreen, mute, successCallback, errorCallback)

videoPath (String) : Concatened Paths of the videos files paths separated with a comma "," , example -> videosPaths = "//android_asset/video1.mp4,//android_asset/video2.mp4,//android_asset/video3.mp4";
loop (Boolean) : true if you want the video to be played in loop
controls (Boolean) : true if you want the controls to appear at the bottom of the video
fullscreen ( Boolean ) : true if you want the video to be played in fullscreen (landscape)
successCallback (Callback) : fired when the video ended correctly
errorCallback (Callback) : fired when there is an error


- STOP A VIDEO
- stopVideo(successCallback, errorCallback)

successCallback (Callback) : fired when the video ended correctly
errorCallback (Callback) : fired when there is an error

- RESET A VIDEO
- resetVideo(successCallback, errorCallback)

successCallback (Callback) : fired when the video ended correctly
errorCallback (Callback) : fired when there is an error
