//
//  LocationsTableViewController.swift
//  KaMPKitiOS
//
//  Created by Sergio Sánchez Sánchez on 1/4/21.
//

import UIKit
import shared

class LocationsTableViewController: UITableViewController {
    
    var viewModel: LocationsViewModel!
    weak var coordinator: MainCoordinator?
    
        
    override func viewDidLoad() {
        super.viewDidLoad()
        print("Locations viewDidLoad CALLED")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        print("Locations viewWillAppear CALLED")
    }
    
}
