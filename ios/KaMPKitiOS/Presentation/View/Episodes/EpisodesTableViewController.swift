//
//  EpisodesTableViewController.swift
//  KaMPKitiOS
//
//  Created by Sergio Sánchez Sánchez on 1/4/21.
//

import UIKit
import shared

class EpisodesTableViewController: UITableViewController {
    
    
    var viewModel: EpisodesViewModel!
    weak var coordinator: MainCoordinator?
    
        
    override func viewDidLoad() {
        super.viewDidLoad()
        print("Episodes viewDidLoad CALLED")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        print("Episodes viewWillAppear CALLED")
    }

    
}
