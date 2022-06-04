//
// Created by Manuel Baez on 03/06/2022.
// Copyright (c) 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TicketsListView: View {
    
    var tickets: [Ticket]
    
    var body: some View {
        List(tickets, id: \.id) { ticket in
            TicketItemView(ticket: ticket, totalPrize: 0)
        }
    }
}

struct TicketsListView_Previews: PreviewProvider {
    static var previews: some View {
        let tickets = [
            Ticket(
                id: 1,
                name: "Test ticket 1",
                number: 0,
                bet: 0.5),
            Ticket(
                id: 1,
                name: "Test ticket 2",
                number: 99999,
                bet: 2000.5)
        ]
        
        TicketsListView(tickets: tickets)
    }
}
