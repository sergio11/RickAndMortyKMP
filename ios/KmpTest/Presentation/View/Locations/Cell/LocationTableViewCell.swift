//
//  LocationTableViewCell.swift
//  KmpTest
//
//  Created by Sergio Sánchez Sánchez on 2/4/21.
//  Copyright © 2021 Sergio Sánchez Sánchez. All rights reserved.
//

import UIKit

class LocationTableViewCell: UITableViewCell {
    
    @IBOutlet weak var locationName: UILabel!
    @IBOutlet weak var locationCreatedAt: UILabel!
    @IBOutlet weak var locationType: UILabel!
    @IBOutlet weak var locationResidents: UILabel!
    @IBOutlet weak var locationDimension: UILabel!
    
    class var nibName: String {
        return String(describing: LocationTableViewCell.self)
    }
    
    class var reuseIdentifier: String {
        return String(describing: LocationTableViewCell.self)
    }
    
    class var rowHeight: CGFloat {
        return CGFloat(200)
    }
    
    var viewModel: LocationTableViewCellViewModel! {
        didSet {
            configureCell()
        }
    }

    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    private func configureCell() {
        locationName.text = viewModel.name
        locationCreatedAt.text = viewModel.createdAt
        locationType.text = "Type: \(viewModel.type)"
        locationResidents.text = "Residents: \(viewModel.residents)"
        locationDimension.text = "Dimension: \(viewModel.dimension)"
    }
    
}
