/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

var app = {
    // Application Constructor
    initialize: function() {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },

    // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function() {
        this.receivedEvent('deviceready');
    },

    // Update DOM on a Received Event
    receivedEvent: function(id) {
        var parentElement = document.getElementById(id);
        var listeningElement = parentElement.querySelector('.listening');
        var receivedElement = parentElement.querySelector('.received');

        listeningElement.setAttribute('style', 'display:none;');
        receivedElement.setAttribute('style', 'display:block;');

        console.log('Received Event: ' + id);
        // console.log('WINDOW: ' + JSON.stringify(window));
        try {
               var videos = '//android_asset/videos/video1.mp4,//android_asset/videos/video2.mp4,//android_asset/videos/video3.mp4';
               var video = '//android_asset/videos/video1.mp4';
              if (window.plugins.phenixVideoPlayer) {
                console.log('INSIDE PLUGINS');


                window.plugins.phenixVideoPlayer.playMultipleVideos(videos, true, false, true,  function(){
                console.log('PHENIX SUCCESS !');
                }, function(error) {
                    console.log('PHENIX ERROR !', error);
                });

              /*   window.plugins.phenixVideoPlayer.playVideo(video, true, false, false, function(){
                                console.log('PHENIX SUCCESS !');
                                }, function(error) {
                                    console.log('PHENIX ERROR !', error);
                                });*/
              }
        } catch( error ) {
            console.log('PHENIX ERROR -> ', error);
        } 
    }
};

app.initialize();