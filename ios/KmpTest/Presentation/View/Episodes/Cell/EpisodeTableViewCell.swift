//
//  EpisodeTableViewCell.swift
//  KmpTest
//
//  Created by Sergio Sánchez Sánchez on 2/4/21.
//  Copyright © 2021 Sergio Sánchez Sánchez. All rights reserved.
//

import UIKit

class EpisodeTableViewCell: UITableViewCell {
    
    
    @IBOutlet weak var episodeName: UILabel!
    @IBOutlet weak var episodeCreatedAt: UILabel!
    @IBOutlet weak var episodeAirDate: UILabel!
    @IBOutlet weak var episodeCharacters: UILabel!
    
    
    class var nibName: String {
        return String(describing: EpisodeTableViewCell.self)
    }
    
    class var reuseIdentifier: String {
        return String(describing: EpisodeTableViewCell.self)
    }
    
    class var rowHeight: CGFloat {
        return CGFloat(200)
    }
    
    var viewModel: EpisodeTableViewCellViewModel! {
        didSet {
            configureCell()
        }
    }

    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    private func configureCell() {
        episodeName.text = viewModel.name
        episodeCreatedAt.text = viewModel.createdAt
        episodeAirDate.text = "Air Date: \(viewModel.airDate)"
        episodeCharacters.text = "Characters: \(viewModel.characters)"
    }
    
}
