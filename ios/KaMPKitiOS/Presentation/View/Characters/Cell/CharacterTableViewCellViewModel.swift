//
//  CharacterTableViewCellViewModel.swift
//  KaMPKitiOS
//
//  Created by Sergio Sánchez Sánchez on 2/4/21.
//

import Foundation
import shared


class CharacterTableViewCellViewModel {
    
    // MARK: - Properties
    private let character: Character
    
    
    var name: String {
        return character.name
    }
    
    var image: String {
        return character.image
    }
    
    
    // MARK: - Init
    init(model: Character) {
        self.character = model
    }
    
}
