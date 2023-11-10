//
//  DateUtils.swift
//  iosApp
//
//  Created by Manuel Baez on 04/06/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

extension Date {
    func formatDate() -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.setLocalizedDateFormatFromTemplate("d MMMM, yyyy")
        return dateFormatter.string(from: self)
    }
}
