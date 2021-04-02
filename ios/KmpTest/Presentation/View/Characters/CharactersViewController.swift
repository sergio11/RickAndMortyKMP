//
//  CharactersViewController.swift
//  KaMPKitiOS
//
//  Created by Sergio Sánchez Sánchez on 2/4/21.
//

import UIKit
import shared

class CharactersViewController: UIViewController {


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
            tableView.register(UINib(nibName: CharacterTableViewCell.nibName, bundle: nil), forCellReuseIdentifier: CharacterTableViewCell.reuseIdentifier)
            tableView.addSubview(refreshControl)
        }
    }
    
    private var characters: [Character] = []
    private var isLoadingPageList = false
    
    var viewModel: CharactersViewModel!
    weak var coordinator: MainCoordinator?

    override func viewDidLoad() {
        super.viewDidLoad()
        bindViewModel()
    }
    
    @objc func refresh(_ sender: AnyObject) {
        viewModel.load(page: 1)
    }
    
    private func bindViewModel() {
        viewModel.state.addObserver(observer: { [weak self] (charactersState: CharactersState?) in
            
            guard let state = charactersState else { return }
            
            if(state is CharactersState.OnLoading) {
                self?.onLoading()
            } else if (state is CharactersState.OnError) {
                let errorState = state as! CharactersState.OnError
                self?.onError(error: errorState.error)
            } else if (state is CharactersState.OnSuccess) {
                let successState = state as! CharactersState.OnSuccess
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
    
    private func onDataLoaded(pageData: PageData<Character>) {
        refreshControl.endRefreshing()
        let characters = pageData.data as! [Character]
        print("onDataLoaded, Page: \(pageData.page), Count: \(characters.count) ")
        if(pageData.page == 1) {
            self.characters = characters
        } else {
            self.characters.append(contentsOf: characters)
        }
        isLoadingPageList = false
        self.tableView.reloadData()
    }

}

extension CharactersViewController: UITableViewDelegate, UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return characters.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: CharacterTableViewCell.reuseIdentifier, for: indexPath) as? CharacterTableViewCell else { fatalError("Cell not found!")}
        cell.viewModel = viewModelForCellAt(indexPath: indexPath)
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return CharacterTableViewCell.rowHeight
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        if (((scrollView.contentOffset.y + scrollView.frame.size.height) > scrollView.contentSize.height ) &&
                characters.count > 0 && !isLoadingPageList && !viewModel.isLastPage()){
            isLoadingPageList = true
            viewModel.loadNextPage()
        }
    }
    
    private func viewModelForCellAt(indexPath: IndexPath) -> CharacterTableViewCellViewModel {
        let character = characters[indexPath.row]
        let cellViewModel = CharacterTableViewCellViewModel(model: character)
        return cellViewModel
    }
}
