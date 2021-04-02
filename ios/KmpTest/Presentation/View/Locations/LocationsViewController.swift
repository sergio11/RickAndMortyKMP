//
//  LocationsViewController.swift
//  KmpTest
//
//  Created by Sergio Sánchez Sánchez on 2/4/21.
//  Copyright © 2021 Sergio Sánchez Sánchez. All rights reserved.
//

import UIKit
import shared

class LocationsViewController: UIViewController {

    @IBOutlet var tableView: UITableView! {
        didSet {
            tableView.delegate = self
            tableView.dataSource = self
            tableView.register(UINib(nibName: LocationTableViewCell.nibName, bundle: nil), forCellReuseIdentifier: LocationTableViewCell.reuseIdentifier)
        }
    }
    
    private var locations: [Location] = [] {
        didSet {
            self.tableView.reloadData()
        }
    }
    
    
    var viewModel: LocationsViewModel!
    weak var coordinator: MainCoordinator?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bindViewModel()
    }
    
    private func bindViewModel() {
        
        viewModel.state.addObserver(observer: { [weak self] (locationState: LocationsState?) in
            
            guard let state = locationState else { return }
            
            if(state is LocationsState.OnLoading) {
                self?.onLoading()
            } else if (state is LocationsState.OnError) {
                let errorState = state as! LocationsState.OnError
                self?.onError(error: errorState.error)
            } else if (state is LocationsState.OnSuccess) {
                let successState = state as! LocationsState.OnSuccess
                self?.onDataLoaded(pageData: successState.pageData)
            }
            

        })
        
        viewModel.loadNextPage()
        
    }
    
    private func onLoading() {
        print("onLoading CALLED")
    }
    
    private func onError(error: KotlinException) {
        print("onError CALLED")
    }
    
    private func onDataLoaded(pageData: PageData<Location>) {
        print("onDataLoaded \(pageData.data.count)")
        self.locations = pageData.data as! [Location]
    }

    
}

extension LocationsViewController: UITableViewDelegate, UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return locations.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: LocationTableViewCell.reuseIdentifier, for: indexPath) as? LocationTableViewCell else { fatalError("Cell not found!")}
        cell.viewModel = viewModelForCellAt(indexPath: indexPath)
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return LocationTableViewCell.rowHeight
    }
    
    private func viewModelForCellAt(indexPath: IndexPath) -> LocationTableViewCellViewModel {
        let location = locations[indexPath.row]
        let cellViewModel = LocationTableViewCellViewModel(model: location)
        return cellViewModel
    }
}
