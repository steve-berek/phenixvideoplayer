cordova.define("cordova-plugin-phenixvideoplayer.PhenixVideoPlayer", function(require, exports, module) {
 // Empty constructor
 var exec = require('cordova/exec');
 var  PhenixVideoPlayer = function(){};

// The function that passes work along to native shells
// Message is a string, duration may be 'long' or 'short'
PhenixVideoPlayer.prototype.playVideo = function(videosFiles, successCallback, errorCallback) {
  var options = {};
  options.videosFiles = videosFiles;
  exec(successCallback, errorCallback, 'PhenixVideoPlayer', 'playVideo', [options]);
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
