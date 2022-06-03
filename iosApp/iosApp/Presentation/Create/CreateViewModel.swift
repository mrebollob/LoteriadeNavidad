//
// Created by Manuel Baez on 03/06/2022.
// Copyright (c) 2022 orgName. All rights reserved.
//

import Foundation
import Promises
import shared
import KMPNativeCoroutinesAsync

class CreateViewModel: ObservableObject {

    @ForceInject<CreateTicket>()
    var createTicket: CreateTicket

    @Published var uiState: CreateUiState = CreateUiState()

    func onSaveTicketClick(name: String, number: String, bet: String) {
        createTicket.execute(
                name: name,
                number: Int32(number) ?? 0,
                bet: Float(bet) ?? 20.0
        ) { _, error in
            if error == nil {
                self.uiState.isCreated = true
            }
        }
    }
}
