//
//  MainCoordinator.swift
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
        let charactersVC = buildCharactersViewController()
        let locationsVC = buildLocationsViewController()
        let episodesVC = buildEpisodesViewController()
        let tabBarController = UITabBarController()
        tabBarController.title = "Rick And Morty KMP"
        tabBarController.viewControllers = [charactersVC, locationsVC, episodesVC]
        tabBarController.selectedViewController = charactersVC
        navigationController.setViewControllers([tabBarController], animated: false)
    }
    
    
    private func buildCharactersViewController() -> CharactersViewController {
        let getCharactersInteract = koin.get(objCClass: GetCharactersInteract.self, qualifier: nil) as! GetCharactersInteract
        let kermit = koin.get(objCClass: Kermit.self, parameter: "CharactersViewModel") as! Kermit
        let charactersViewModel = CharactersViewModel(getCharactersInteract: getCharactersInteract, kermit: kermit)
        
        let charactersTableVC = CharactersViewController()
        charactersTableVC.viewModel = charactersViewModel
        charactersTableVC.coordinator = self
        
        let item = UITabBarItem()
        item.title = "Characters"
        item.image = UIImage(systemName: "person.3.fill")
        charactersTableVC.tabBarItem = item
        
        return charactersTableVC
    }
    
    private func buildLocationsViewController() -> LocationsViewController {
        let getLocationsInteract = koin.get(objCClass: GetLocationsInteract.self, qualifier: nil) as! GetLocationsInteract
        let locationsViewModel = LocationsViewModel(getLocationsInteract: getLocationsInteract)
        let locationsTableVC = LocationsViewController()
        locationsTableVC.viewModel = locationsViewModel
        locationsTableVC.coordinator = self
        let item = UITabBarItem()
        item.title = "Locations"
        item.image = UIImage(systemName: "location.fill")
        locationsTableVC.tabBarItem = item
        return locationsTableVC
        
    }
    
    private func buildEpisodesViewController() -> EpisodesViewController {
        let getEpisodesInteract = koin.get(objCClass: GetEpisodesInteract.self, qualifier: nil) as! GetEpisodesInteract
        let episodesViewModel = EpisodesViewModel(getEpisodesInteract: getEpisodesInteract)
        let episodesTableVC = EpisodesViewController()
        episodesTableVC.viewModel = episodesViewModel
        episodesTableVC.coordinator = self
        let item = UITabBarItem()
        item.title = "Episodes"
        item.image = UIImage(systemName: "play.tv.fill")
        episodesTableVC.tabBarItem = item
        return episodesTableVC
    }
    

}
