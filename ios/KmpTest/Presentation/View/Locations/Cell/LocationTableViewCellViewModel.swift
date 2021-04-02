//
//  LocationTableViewCellViewModel.swift
//  KmpTest
//
//  Created by Sergio Sánchez Sánchez on 2/4/21.
//  Copyright © 2021 Sergio Sánchez Sánchez. All rights reserved.
//

import Foundation
import shared

class LocationTableViewCellViewModel {
    
    // MARK: - Properties
    private let location: Location
    
    var name: String {
        return location.name
    }
    
    var createdAt: String {
        return location.created
    }
    
    var type: String {
        return location.type
    }
    
    var residents: Int32 {
        return location.residents
    }
    
    var dimension: String {
        return location.dimension
    }
    
    
    // MARK: - Init
    init(model: Location) {
        self.location = model
    }
    
}
