//
//  TicketItemView.swift
//  iosApp
//
//  Created by Manuel Baez on 04/06/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TicketItemView: View {
    
    var ticket: Ticket
    var totalPrize: Float?
    
    func getPriceText() -> String {
        if totalPrize == nil {
            return "-"
        } else {
            return String(format: "%.2f", totalPrize!)
        }
    }
    
    var body: some View {
        VStack{
//            LREdgeCutShapeView(cornerRadius: 16)
////                .foregroundColor(Color("Grey4"))
//                .fill(
//                    Color("Grey4"),
//                    style: FillStyle(eoFill: false, antialiased: false)
//                )
//                .frame(maxWidth: .infinity)
//                .frame(height: 24.0)
            
            HStack {
                VStack(alignment: .leading, spacing: 0){
                    Text("Nombre")
                        .font(Font.custom("Montserrat-Medium", size: 14))
                        .foregroundColor(Color("Grey4"))
                    
                    Text(ticket.name)
                        .font(Font.custom("Montserrat-SemiBold", size: 24))
                        .foregroundColor(Color("OnBackground"))
                }
                .frame(maxWidth: .infinity, alignment: .topLeading)
                .padding()
                
                VStack(alignment: .trailing, spacing: 0){
                    Text("Número")
                        .font(Font.custom("Montserrat-Medium", size: 14))
                        .foregroundColor(Color("Grey4"))
                    
                    Text(String(format: "%05d", ticket.number))
                        .font(Font.custom("Montserrat-SemiBold", size: 24))
                        .foregroundColor(Color("OnBackground"))
                }
                .frame(maxWidth: .infinity, alignment: .topTrailing)
                .padding()
            }
            
            HStack {
                VStack(alignment: .leading, spacing: 0){
                    Text("Jugado")
                        .font(Font.custom("Montserrat-Medium", size: 14))
                        .foregroundColor(Color("Grey4"))
                    
                    Text( String(format: "%.2f", ticket.bet))
                        .font(Font.custom("Montserrat-SemiBold", size: 24))
                        .foregroundColor(Color("OnBackground"))
                }
                .frame(maxWidth: .infinity, alignment: .topLeading)
                .padding()
                
                VStack(alignment: .trailing, spacing: 0){
                    Text("Ganado")
                        .font(Font.custom("Montserrat-Medium", size: 14))
                        .foregroundColor(Color("Grey4"))
                    
                    Text(getPriceText())
                        .font(Font.custom("Montserrat-SemiBold", size: 24))
                        .foregroundColor(Color("OnBackground"))
                }
                .frame(maxWidth: .infinity, alignment: .topTrailing)
                .padding()
            }
        }
        .frame(maxWidth: .infinity)
        .background(Color("Surface"))
        .modifier(CardModifier())
        .padding(.horizontal, 16)
        .padding(.vertical, 4)
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
