//
//  koin.swift
//  KaMPKitiOS
//
//  Created by Sergio Sánchez Sánchez on 29/3/21.
//  Copyright © 2021 Touchlab. All rights reserved.
//

import Foundation
import shared

func startKoin() {
  
    let userDefaults = UserDefaults(suiteName: "KAMPSTARTER_SETTINGS")!
    let iosAppInfo = IosAppInfo()
    let doOnStartup = { NSLog("Hello from iOS/Swift!") }
    
    let koinApplication = KoinIOSKt.doInitKoinIos(userDefaults: userDefaults, appInfo: iosAppInfo, doOnStartup: doOnStartup)
    
    _koin = koinApplication.koin
}

private var _koin: Koin_coreKoin? = nil
var koin: Koin_coreKoin {
    return _koin!
}


class IosAppInfo : AppInfo {
    let appId: String = Bundle.main.bundleIdentifier!
}
