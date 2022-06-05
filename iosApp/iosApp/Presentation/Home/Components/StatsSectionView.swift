//
//  StatsSectionView.swift
//  iosApp
//
//  Created by Manuel Baez on 05/06/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct StatsSectionView: View {
    
    let totalBet: Float
    let totalPrize: Float?
    
    func getTotalPriceText() -> String {
        if totalPrize == nil {
            return "-"
        } else {
            return String(format: "%.2f", totalPrize!)
        }
    }
    
    var body: some View {
        HStack {
            VStack{
                Text("Total jugado")
                    .font(Font.custom("montserrat_medium", size: 14))
                    .foregroundColor(Color("Grey4"))
                
                Text( String(format: "%.2f", totalBet))
                    .font(Font.custom("montserrat_semibold", size: 24))
                    .foregroundColor(Color("OnBackground"))
            }
            .frame(maxWidth: .infinity)
            .padding()
            
            VStack{
                Text("Total ganado")
                    .font(Font.custom("montserrat_medium", size: 14))
                    .foregroundColor(Color("Grey4"))
                
                Text(getTotalPriceText())
                    .font(Font.custom("montserrat_semibold", size: 24))
                    .foregroundColor(Color("OnBackground"))
            }
            .frame(maxWidth: .infinity)
            .padding()
        }
        .frame(maxWidth: .infinity)
        .cornerRadius(8)
        .overlay(
            RoundedRectangle(cornerRadius: 8)
                .stroke(Color("Grey4"), lineWidth: 1)
        )
        .padding(.horizontal)
    }
}

struct StatsSectionView_Previews: PreviewProvider {
    static var previews: some View {
        StatsSectionView(
            totalBet: 20,
            totalPrize: nil
        )
    }
}