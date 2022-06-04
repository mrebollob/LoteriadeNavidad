//
//  CountdownSectionView.swift
//  iosApp
//
//  Created by Manuel Baez on 04/06/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct CountdownSectionView: View {
    
    var today: Date
    var daysToLotteryDraw: Int
    
    func getCountDownText() -> String {
        var daysToLotteryDrawText = ""
        if daysToLotteryDraw == 0 {
            daysToLotteryDrawText = "Hoy es el día del sorteo"
        } else if daysToLotteryDraw < 0 {
            daysToLotteryDrawText = "El sorteo ha finalizado"
        } else if daysToLotteryDraw == 1 {
            daysToLotteryDrawText = "1 día para el sorteo"
        } else {
            daysToLotteryDrawText = "\(daysToLotteryDraw) días para el sorteo"
        }
        
        return daysToLotteryDrawText
    }
    
    var body: some View {
        VStack{
            Text("4 June, 2022")
                .font(Font.custom("montserrat_semibold", size: 24))
//                .foregroundColor(Color("Grey7"))
            
            Text(getCountDownText())
                .font(Font.custom("montserrat_semibold", size: 24))
//                .foregroundColor(Color("Grey7"))
        }
    }
}

struct CountdownSectionView_Previews: PreviewProvider {
    static var previews: some View {
        CountdownSectionView(
            today: Date(),
            daysToLotteryDraw: 50
        )
    }
}
