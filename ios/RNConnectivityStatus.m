//
//  RNConnectivityStatus.m
//
//  Created by Mattia Panzeri on 10/10/2017.
//  Latest changes by Federico Boschini on 08/29/2018
//  Copyright © 2017 Near Srl. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "RNConnectivityStatus.h"



// Location permission status
NSString* const RNCS_PERMISSION_LOCATION_GRANTED_ALWAYS = @"always";
NSString* const RNCS_PERMISSION_LOCATION_GRANTED_WHEN_IN_USE = @"whenInUse";
NSString* const RNCS_PERMISSION_LOCATION_DENIED = @"denied";

@implementation RNConnectivityStatus

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

+ (BOOL)requiresMainQueueSetup
{
    return YES;
}

RCT_EXPORT_MODULE()

- (NSDictionary *)constantsToExport
{
    return @{
        @"Permissions": @{
                @"always": RNCS_PERMISSION_LOCATION_GRANTED_ALWAYS,
                @"whenInUse": RNCS_PERMISSION_LOCATION_GRANTED_ALWAYS,
                @"denied": RNCS_PERMISSION_LOCATION_DENIED
        }
    };
}


// MARK: Location Permissions

- (LocationPermissionState)isLocationPermissionGranted {
    switch (CLLocationManager.authorizationStatus) {
        case kCLAuthorizationStatusAuthorizedWhenInUse:
            return LocationPermissionWhenInUse;
        case kCLAuthorizationStatusAuthorizedAlways:
            return LocationPermissionAlways;
        default:
            return LocationPermissionOff;
    }
}

RCT_EXPORT_METHOD(isLocationPermissionGranted:(RCTPromiseResolveBlock) resolve
                  rejecter:(RCTPromiseRejectBlock) reject) {
    LocationPermissionState state = [self isLocationPermissionGranted];
    switch (state) {
        case LocationPermissionWhenInUse:
            resolve(RNCS_PERMISSION_LOCATION_GRANTED_WHEN_IN_USE);
            break;
        case LocationPermissionAlways:
            resolve(RNCS_PERMISSION_LOCATION_GRANTED_ALWAYS);
            break;
        case LocationPermissionOff:
            resolve(RNCS_PERMISSION_LOCATION_DENIED);
            break;
        default:
            reject(@"LOCATION_PERMISSION_CHECK_ERROR", @"Can't check location permission", nil);
            break;
    }
}

// MARK: Location Services

RCT_EXPORT_METHOD(areLocationServicesEnabled:(RCTPromiseResolveBlock) resolve
                  rejecter:(RCTPromiseRejectBlock) reject) {
    resolve(@(CLLocationManager.locationServicesEnabled));
}

@end
