//
//  MainCoordinator.swift
//  KaMPKitiOS
//
//  Created by Sergio Sánchez Sánchez on 30/3/21.
//

import Foundation
import UIKit
import shared

/// Panrent of all coordinators, responsible to set flows.
final class MainCoordinator: Coordinator {
    
    var childsCoordinator: [String: Coordinator] = [:]
    let navigationController: UINavigationController
    
    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
    }
    

    
    func start() {
        
        let homeViewController = HomeViewController()
        
        let getCharactersInteract = koin.get(objCClass: GetCharactersInteract.self, qualifier: nil) as! GetCharactersInteract
        let kermit = koin.get(objCClass: Kermit.self, parameter: "CharactersViewModel") as! Kermit
        
        let charactersViewModel = CharactersViewModel(getCharactersInteract: getCharactersInteract, kermit: kermit)
        
        homeViewController.viewModel = charactersViewModel
        homeViewController.coordinator = self
        
        
        navigationController.setViewControllers([homeViewController], animated: false)
        
    }
    
}
