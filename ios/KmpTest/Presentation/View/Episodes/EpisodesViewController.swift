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
    
    lazy var refreshControl: UIRefreshControl = {
        let refreshControl = UIRefreshControl()
        refreshControl.attributedTitle = NSAttributedString(string: "Pull to refresh")
        refreshControl.addTarget(self, action: #selector(self.refresh(_:)), for: .valueChanged)
        return refreshControl
    }()
    
    @IBOutlet weak var tableView: UITableView! {
        didSet {
            tableView.delegate = self
            tableView.dataSource = self
            tableView.register(UINib(nibName: EpisodeTableViewCell.nibName, bundle: nil), forCellReuseIdentifier: EpisodeTableViewCell.reuseIdentifier)
            tableView.addSubview(refreshControl)
        }
    }
    
    private var episodes: [Episode] = []
    private var isLoadingPageList = false
    
    
    var viewModel: EpisodesViewModel!
    weak var coordinator: MainCoordinator?

    override func viewDidLoad() {
        super.viewDidLoad()
        bindViewModel()
    }
    
    @objc func refresh(_ sender: AnyObject) {
        viewModel.load(page: 1)
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
        viewModel.load(page: 1)
    }
    
    private func onLoading() {
        refreshControl.beginRefreshing()
    }
    
    private func onError(error: KotlinException) {
        isLoadingPageList = false
        refreshControl.endRefreshing()
    }
    
    private func onDataLoaded(pageData: PageData<Episode>) {
        refreshControl.endRefreshing()
        let episodes = pageData.data as! [Episode]
        print("onDataLoaded, Page: \(pageData.page), Count: \(episodes.count) ")
        if(pageData.page == 1) {
            self.episodes = episodes
        } else {
            self.episodes.append(contentsOf: episodes)
        }
        isLoadingPageList = false
        self.tableView.reloadData()
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
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        if (((scrollView.contentOffset.y + scrollView.frame.size.height) > scrollView.contentSize.height ) &&
                episodes.count > 0 && !isLoadingPageList && !viewModel.isLastPage()){
            isLoadingPageList = true
            viewModel.loadNextPage()
        }
    }
    
    private func viewModelForCellAt(indexPath: IndexPath) -> EpisodeTableViewCellViewModel {
        let episode = episodes[indexPath.row]
        let cellViewModel = EpisodeTableViewCellViewModel(model: episode)
        return cellViewModel
    }
}
