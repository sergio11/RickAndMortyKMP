//
//  CharacterTableViewCell.swift
//  KaMPKitiOS
//
//  Created by Sergio Sánchez Sánchez on 2/4/21.
//  Copyright © 2021 Touchlab. All rights reserved.
//

import UIKit
import Nuke

class CharacterTableViewCell: UITableViewCell {

    @IBOutlet weak var characterName: UILabel!
    @IBOutlet weak var characterImage: UIImageView!
    @IBOutlet weak var characterState: UILabel!
    @IBOutlet weak var species: UILabel!
    @IBOutlet weak var lastKnownLocation: UILabel!
    
    class var nibName: String {
        return String(describing: CharacterTableViewCell.self)
    }
    
    class var reuseIdentifier: String {
        return String(describing: CharacterTableViewCell.self)
    }
    
    class var rowHeight: CGFloat {
        return CGFloat(200)
    }
    
    var viewModel: CharacterTableViewCellViewModel! {
        didSet {
            configureCell()
        }
    }

    override func awakeFromNib() {
        super.awakeFromNib()
    }
    

    private func configureCell() {
        characterName.text = viewModel.name
        Nuke.loadImage(with: URL(string: viewModel.image)!, into: characterImage)
        lastKnownLocation.text = "Last Known location: \(viewModel.lastKnownLocation)"
        species.text = "Specie: \(viewModel.species)"
        characterState.text = viewModel.status.name
        switch viewModel.status {
            case .alive:
                characterState.textColor = UIColor(named: "character_alive")
            case .dead:
                characterState.textColor = UIColor(named: "character_dead")
            default:
                break
        }
        
    }
    
}
