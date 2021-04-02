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
    
    lazy var refreshControl: UIRefreshControl = {
        let refreshControl = UIRefreshControl()
        refreshControl.attributedTitle = NSAttributedString(string: "Pull to refresh")
        refreshControl.addTarget(self, action: #selector(self.refresh(_:)), for: .valueChanged)
        return refreshControl
    }()

    @IBOutlet var tableView: UITableView! {
        didSet {
            tableView.delegate = self
            tableView.dataSource = self
            tableView.register(UINib(nibName: LocationTableViewCell.nibName, bundle: nil), forCellReuseIdentifier: LocationTableViewCell.reuseIdentifier)
            tableView.addSubview(refreshControl)
        }
    }
    
    private var locations: [Location] = []
    private var isLoadingPageList = false
    
    
    var viewModel: LocationsViewModel!
    weak var coordinator: MainCoordinator?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bindViewModel()
    }
    
    @objc func refresh(_ sender: AnyObject) {
        viewModel.load(page: 1)
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
        viewModel.load(page: 1)
    }
    
    private func onLoading() {
        refreshControl.beginRefreshing()
    }
    
    private func onError(error: KotlinException) {
        isLoadingPageList = false
        refreshControl.endRefreshing()
    }
    
    private func onDataLoaded(pageData: PageData<Location>) {
        refreshControl.endRefreshing()
        let locations = pageData.data as! [Location]
        print("onDataLoaded, Page: \(pageData.page), Count: \(locations.count) ")
        if(pageData.page == 1) {
            self.locations = locations
        } else {
            self.locations.append(contentsOf: locations)
        }
        isLoadingPageList = false
        self.tableView.reloadData()
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
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        if (((scrollView.contentOffset.y + scrollView.frame.size.height) > scrollView.contentSize.height ) &&
                locations.count > 0 && !isLoadingPageList && !viewModel.isLastPage()){
            isLoadingPageList = true
            viewModel.loadNextPage()
        }
    }
    
    private func viewModelForCellAt(indexPath: IndexPath) -> LocationTableViewCellViewModel {
        let location = locations[indexPath.row]
        let cellViewModel = LocationTableViewCellViewModel(model: location)
        return cellViewModel
    }
}
