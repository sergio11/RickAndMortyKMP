//
//  Coordinator.swift
//  KaMPKitiOS
//
//  Created by Sergio Sánchez Sánchez on 30/3/21.
//

import Foundation
import UIKit

/**
 Pattern Coordinator responsible of navigation flow and dependencies injection
 */
protocol Coordinator {
    var childsCoordinator: [String: Coordinator] { get }
    var navigationController: UINavigationController { get }
    func start()
}
