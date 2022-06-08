//
//  CardModifier.swift
//  iosApp
//
//  Created by Manuel Baez on 08/06/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct CardModifier: ViewModifier {
    func body(content: Content) -> some View {
        content
            .cornerRadius(8)
            .shadow(color: Color.black.opacity(0.1), radius: 8, x: 0, y: 0)
    }
}
