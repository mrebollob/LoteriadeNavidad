//
// Created by Manuel Baez on 03/06/2022.
// Copyright (c) 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TicketItemView: View {

    var ticket: Ticket
    var totalPrize: Float?

    var body: some View {
        VStack {
            VStack {
                Text(ticket.name)
                        .font(.headline)
                        .lineLimit(2)
                Text(ticket.name)
                        .font(.subheadline)
                        .foregroundColor(.secondary)
            }
                    .padding()
            Spacer()
        }
                .frame(height: 400)
                .cornerRadius(8)
                .overlay(
                        RoundedRectangle(cornerRadius: 8)
                                .stroke(Color(.sRGB, red: 150 / 255, green: 150 / 255, blue: 150 / 255, opacity: 0.1), lineWidth: 1)
                )
                .shadow(radius: 1)
    }
}

struct TicketItemView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            TicketItemView(
                    ticket: Ticket(id: 1, name: "Test ticket 1", number: 0, bet: 0.5),
                    totalPrize: nil
            )
                    .previewLayout(.fixed(width: 300, height: 510))
            TicketItemView(
                    ticket: Ticket(id: 1, name: "Test ticket 1", number: 0, bet: 0.5),
                    totalPrize: 20000000
            )
                    .previewLayout(.fixed(width: 300, height: 510))
        }
    }
}
