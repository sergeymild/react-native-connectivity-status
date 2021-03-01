package com.nearit.connectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RNConnectivityStatusModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    // Location permission status
    private static final String PERMISSION_LOCATION_GRANTED = "always";
    private static final String PERMISSION_LOCATION_DENIED = "denied";

    public RNConnectivityStatusModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNConnectivityStatus";
    }

    @javax.annotation.Nullable
    @Override
    public Map<String, Object> getConstants() {
        return Collections.unmodifiableMap(new HashMap<String, Object>() {
            {
                put("LocationGrantedAlways", PERMISSION_LOCATION_GRANTED);
                put("LocationDenied", PERMISSION_LOCATION_DENIED);
            }
        });
    }

    @ReactMethod
    public void areLocationServicesEnabled(final Promise promise) {
        try {
            promise.resolve(checkLocationServices());
        } catch (Exception e) {
            promise.reject("LOCATION_CHECK_ERROR", e.getMessage());
        }
    }

    @ReactMethod
    public void isLocationPermissionGranted(final Promise promise) {
        try {
            if (checkLocationPermission()) {
                promise.resolve(PERMISSION_LOCATION_GRANTED);
            } else {
                promise.resolve(PERMISSION_LOCATION_DENIED);
            }
        } catch (Exception e) {
            promise.reject("LOCATION_PERMISSION_CHECK_ERROR", e.getMessage());
        }
    }

    private boolean checkLocationServices() {
        final LocationManager locationManager = (LocationManager) getReactApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        return (locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                | (locationManager != null && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER));
    }

    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(getReactApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
}
