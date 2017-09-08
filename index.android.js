/**
<<<<<<< HEAD
 * Copyright (c) 2015-present, Viro, Inc.
=======
 * Copyright (c) 2016-present, Viro, Inc.
>>>>>>> a3de7a631f3627894b2e5d8f06177c22f85a617c
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
import React, { Component } from 'react';
import {
  AppRegistry,
<<<<<<< HEAD
=======
  StyleSheet,
  View,
>>>>>>> a3de7a631f3627894b2e5d8f06177c22f85a617c
} from 'react-native';

import {
  ViroSceneNavigator,
<<<<<<< HEAD
} from 'react-viro';

var InitialScene = require('./js/HelloWorldScene');

export default class ViroSample extends Component {
  render() {
    return (
      <ViroSceneNavigator apiKey="ANDROID_DOESNT_YET_NEED_A_KEY"
        initialScene={{scene: InitialScene}}
        />
    );
  }
}

AppRegistry.registerComponent('ViroMediaAppBeta', () => ViroSample);

=======
  ViroScene,
  ViroBox,
} from 'react-viro';

export default class ViroSample extends Component {
  render() {
    scene = this._getScene(this.props.initialScene);
    return (
      <ViroSceneNavigator apiKey="25E18786-5C8C-4084-9DFA-00BDA03BE625"
        initialScene={{scene: scene}}
        initialSceneKey="homescreen"
        vrModeEnabled={this.props.vrMode}
        debug={this.props.debug} onExitViro={this._onExit}
    />
    );
  }
  _getScene(sceneName) {
    // We're doing it this way so that we only load in the scenes that we need.
    if (sceneName == '360 Photo Tour') {
      return require('./js/360PhotoTour/MainScene');
    } else if (sceneName == 'Inside the Human Body') {
      return require('./js/HumanBody/Heart');
    } else if (sceneName == 'Viro Media Player') {
      return require('./js/ViroMediaPlayer/ViroTheatre');
    } else if (sceneName == 'Flickr Photo Explorer') {
      return require('./js/HelloWorld/HelloWorldScene'); // TODO: replace this scene with the right one
    } else if (sceneName == 'MainMenu') {
      return require('./js/HomeScreen/HomeMenuScene');
    } else {
      return require('./js/HomeScreen/HomeMenuScene');
    }
  }

  _onExit() {
    
  }
}

AppRegistry.registerComponent('ViroSample', () => ViroSample);
>>>>>>> a3de7a631f3627894b2e5d8f06177c22f85a617c
