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
        
    }
    
}
