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
    
    var lastKnownLocation: String {
        return character.location
    }
    
    var species: String {
        return character.species
    }
    
    var status: CharacterStatusEnum {
        return character.status
    }
    
    
    // MARK: - Init
    init(model: Character) {
        self.character = model
    }
    
}
