//
//  AppDelegate.swift
//  KaMPKitiOS
//

import UIKit
import shared

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    var window: UIWindow?


    func application(_ application: UIApplication, didFinishLaunchingWithOptions
        launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {

              
        // Manually launch storyboard so that ViewController doesn't initialize before Koin
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let viewController = storyboard.instantiateViewController(identifier: "BreedsViewController")
        
        self.window = UIWindow(frame: UIScreen.main.bounds)
        self.window?.rootViewController = viewController
        self.window?.makeKeyAndVisible()
        
        return true
    }
}

