//
//  CharactersTableViewController.swift
//  KaMPKitiOS
//
//  Created by Sergio Sánchez Sánchez on 1/4/21.
//

import UIKit
import shared

class CharactersTableViewController: UITableViewController {
    
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
    }
    
    
}
