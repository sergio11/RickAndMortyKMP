//
//  CharactersViewController.swift
//  KaMPKitiOS
//
//  Created by Sergio Sánchez Sánchez on 2/4/21.
//

import UIKit
import shared

class CharactersViewController: UIViewController {


    @IBOutlet weak var tableView: UITableView! {
        didSet {
            tableView.delegate = self
            tableView.dataSource = self
            tableView.register(UINib(nibName: CharacterTableViewCell.nibName, bundle: nil), forCellReuseIdentifier: CharacterTableViewCell.reuseIdentifier)
        }
    }
    
    private var characters: [Character] = [] {
        didSet {
            self.tableView.reloadData()
        }
    }
    
    var viewModel: CharactersViewModel!
    weak var coordinator: MainCoordinator?

    override func viewDidLoad() {
        super.viewDidLoad()
        bindViewModel()
        print("Characters viewDidLoad CALLED")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        print("Characters viewWillAppear CALLED")
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
        
        viewModel.loadNextPage()
        
    }
    
    private func onLoading() {
        print("onLoading CALLED")
    }
    
    private func onError(error: KotlinException) {
        print("onError CALLED")
    }
    
    private func onDataLoaded(pageData: PageData<Character>) {
        print("onDataLoaded \(pageData.data.count)")
        self.characters = pageData.data as! [Character]
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
    
    
    private func viewModelForCellAt(indexPath: IndexPath) -> CharacterTableViewCellViewModel {
        let character = characters[indexPath.row]
        let cellViewModel = CharacterTableViewCellViewModel(model: character)
        return cellViewModel
    }
    
    
}
