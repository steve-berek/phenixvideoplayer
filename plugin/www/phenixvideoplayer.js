cordova.define("cordova-plugin-phenixvideoplayer.PhenixVideoPlayer", function(require, exports, module) {
    // Empty constructor
    var exec = require('cordova/exec');
    var  PhenixVideoPlayer = function(){};
   
   // The function that passes work along to native shells
   // Message is a string, duration may be 'long' or 'short'
   PhenixVideoPlayer.prototype.playVideo = function(videoPath, loop, controls, fullscreen, successCallback, errorCallback) {
     var options = {};
     options.videoPath = videoPath;
     options.loop = loop;
     options.controls = controls;
     options.fullscreen = fullscreen;
     exec(successCallback, errorCallback, 'PhenixVideoPlayer', 'playVideo', [options]);
   }
   PhenixVideoPlayer.prototype.playMultipleVideos = function(videosPaths, loop, controls, fullscreen, successCallback, errorCallback) {
     var options = {};
     options.videosPaths = videosPaths;
       options.loop = loop;
       options.controls = controls;
       options.fullscreen = fullscreen;
     exec(successCallback, errorCallback, 'PhenixVideoPlayer', 'playMultipleVideos', [options]);
   }
   module.exports = new PhenixVideoPlayer();
   // Installation constructor that binds ToastyPlugin to window
   PhenixVideoPlayer.install = function() {
     if (!window.plugins) {
       window.plugins = {};
     }
     window.plugins.phenixVideoPlayer = new PhenixVideoPlayer();
     return window.plugins.phenixVideoPlayer;
   };
   cordova.addConstructor(PhenixVideoPlayer.install); 
   
   /* var exec = require("cordova/exec");
   module.exports = {
       playVideo: function (path, successCallback, errorCallback) {
           exec(successCallback, errorCallback, "PhenixVideoPlayer", "playVideo", [path]);
       }
   }; */
   });
   