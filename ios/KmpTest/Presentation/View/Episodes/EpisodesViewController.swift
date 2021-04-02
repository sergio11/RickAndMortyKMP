//
//  EpisodesViewController.swift
//  KmpTest
//
//  Created by Sergio Sánchez Sánchez on 2/4/21.
//  Copyright © 2021 Sergio Sánchez Sánchez. All rights reserved.
//

import UIKit
import shared

class EpisodesViewController: UIViewController {
    
    @IBOutlet weak var tableView: UITableView! {
        didSet {
            tableView.delegate = self
            tableView.dataSource = self
            tableView.register(UINib(nibName: EpisodeTableViewCell.nibName, bundle: nil), forCellReuseIdentifier: EpisodeTableViewCell.reuseIdentifier)
        }
    }
    
    private var episodes: [Episode] = [] {
        didSet {
            self.tableView.reloadData()
        }
    }
    
    var viewModel: EpisodesViewModel!
    weak var coordinator: MainCoordinator?

    override func viewDidLoad() {
        super.viewDidLoad()
        bindViewModel()
    }
    
    private func bindViewModel() {
        
        viewModel.state.addObserver(observer: { [weak self] (episodeState: EpisodesState?) in
            
            guard let state = episodeState else { return }
            
            if(state is EpisodesState.OnLoading) {
                self?.onLoading()
            } else if (state is EpisodesState.OnError) {
                let errorState = state as! EpisodesState.OnError
                self?.onError(error: errorState.error)
            } else if (state is EpisodesState.OnSuccess) {
                let successState = state as! EpisodesState.OnSuccess
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
    
    private func onDataLoaded(pageData: PageData<Episode>) {
        print("onDataLoaded \(pageData.data.count)")
        self.episodes = pageData.data as! [Episode]
    }

}

extension EpisodesViewController: UITableViewDelegate, UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return episodes.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: EpisodeTableViewCell.reuseIdentifier, for: indexPath) as? EpisodeTableViewCell else { fatalError("Cell not found!")}
        cell.viewModel = viewModelForCellAt(indexPath: indexPath)
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return EpisodeTableViewCell.rowHeight
    }
    
    private func viewModelForCellAt(indexPath: IndexPath) -> EpisodeTableViewCellViewModel {
        let episode = episodes[indexPath.row]
        let cellViewModel = EpisodeTableViewCellViewModel(model: episode)
        return cellViewModel
    }
}
