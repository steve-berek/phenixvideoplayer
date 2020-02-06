// Empty constructor
function PhenixVideoPlayer() {}

// The function that passes work along to native shells
// Message is a string, duration may be 'long' or 'short'
PhenixVideoPlayer.prototype.show = function(message, duration, successCallback, errorCallback) {
  var options = {};
  options.message = message;
  options.duration = duration;
  cordova.exec(successCallback, errorCallback, 'PhenixVideoPlayer', 'show', [options]);
}

// Installation constructor that binds ToastyPlugin to window
PhenixVideoPlayer.install = function() {
  if (!window.plugins) {
    window.plugins = {};
  }
  window.plugins.phenixVideoPlayer = new PhenixVideoPlayer();
  return window.plugins.phenixVideoPlayer;
};
cordova.addConstructor(PhenixVideoPlayer.install);