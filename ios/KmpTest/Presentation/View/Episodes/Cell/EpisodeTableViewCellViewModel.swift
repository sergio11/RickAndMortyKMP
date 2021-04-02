//
//  EpisodeTableViewCellViewModel.swift
//  KmpTest
//
//  Created by Sergio Sánchez Sánchez on 2/4/21.
//  Copyright © 2021 Sergio Sánchez Sánchez. All rights reserved.
//

import Foundation
import shared

class EpisodeTableViewCellViewModel {
    
    //MARK: properties
    private var episode: Episode
    
    var name: String {
        return episode.name
    }
    
    var createdAt: String {
        return episode.created
    }
    
    var airDate: String {
        return episode.airDate
    }
    
    var characters: Int {
        return Int(episode.characters)
    }
    
    
    //MARK: init
    init(model: Episode) {
        self.episode = model
    }
    
}
