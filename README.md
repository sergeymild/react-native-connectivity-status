
# React-native-connectivity-status
> A [React Native](https://facebook.github.io/react-native/) module to check Location status on Android and iOS

[![license](https://img.shields.io/github/license/nearit/react-native-connectivity-status.svg)](https://github.com/nearit/react-native-connectivity-status/blob/master/LICENSE.md)
[![Build Status](https://travis-ci.org/nearit/react-native-connectivity-status.svg)](https://travis-ci.org/nearit/react-native-connectivity-status)
[![npm](https://img.shields.io/npm/v/react-native-connectivity-status.svg)](https://www.npmjs.com/package/react-native-connectivity-status)

[![React Native](https://img.shields.io/badge/RN-0.41.2+-green.svg)](https://facebook.github.io/react-native/)
![platforms](https://img.shields.io/badge/platforms-Android%20%7C%20iOS-brightgreen.svg)

[![Gitter](https://img.shields.io/gitter/room/nearit/Lobby.svg)](https://gitter.im/nearit/Lobby)

## Getting started

Add `react-native-connectivity-status` module to your project

`$ yarn add react-native-connectivity-status`

<br/>

## Usage

### Check Status
Interactively check Location Services and Bluetooth status
```js
import ConnectivityManager from 'react-native-connectivity-status'

// Check Location permission
const isGranted = await ConnectivityManager.isLocationPermissionGranted()

```
