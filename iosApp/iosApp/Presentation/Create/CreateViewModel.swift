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
    
    func onSaveTicketClick(name: String, numberText: String, betText: String) {
        let number = Int32(numberText)
        if number == nil {
            uiState.errorMessages = "Introduce un número válido"
            uiState.showError = true
            return
        }
        
        let bet = Float(betText)
        if bet == nil || (bet ?? 0) <= 0 {
            uiState.errorMessages = "Introduce una cantidad válida"
            uiState.showError = true
            return
        }
        
        if number != nil && bet != nil {
            createTicket.execute(
                name: name,
                number: number!,
                bet: bet!
            ) { _, error in
                if error == nil {
                    self.uiState.isCreated = true
                }
            }
        }
    }
}
