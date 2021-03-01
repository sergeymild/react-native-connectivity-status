//
//  RNConnectivityStatus.h
//
//  Created by Mattia Panzeri on 10/10/2017.
//  Latest changes by Federico Boschini on 08/29/2018
//  Copyright © 2017 Near Srl. All rights reserved.
//

#import <React/RCTBridgeModule.h>
#import <React/RCTUtils.h>

#import <CoreLocation/CoreLocation.h>

@interface RNConnectivityStatus : NSObject <RCTBridgeModule>

typedef NS_ENUM(NSInteger, LocationPermissionState) {
    LocationPermissionOff,
    LocationPermissionWhenInUse,
    LocationPermissionAlways
};

@end
