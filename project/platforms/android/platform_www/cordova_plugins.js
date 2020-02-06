cordova.define('cordova/plugin_list', function(require, exports, module) {
  module.exports = [
    {
      "id": "cordova-plugin-phenixvideoplayer.PhenixVideoPlayer",
      "file": "plugins/cordova-plugin-phenixvideoplayer/www/phenixvideoplayer.js",
      "pluginId": "cordova-plugin-phenixvideoplayer",
      "clobbers": [
        "window.plugins.phenixvideoplayer"
      ]
    }
  ];
  module.exports.metadata = {
    "cordova-plugin-whitelist": "1.3.4",
    "cordova-plugin-phenixvideoplayer": "1.0.0"
  };
});