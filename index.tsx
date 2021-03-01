
import { NativeModules } from 'react-native'

const { RNConnectivityStatus } = NativeModules

export class ConnectivityManager {

  static isLocationPermissionGranted = async (): Promise<boolean> => {
    const response = await RNConnectivityStatus.isLocationPermissionGranted()
    return response !== "denied"
  }

}
