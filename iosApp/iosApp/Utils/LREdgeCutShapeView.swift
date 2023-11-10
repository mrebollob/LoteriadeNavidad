//
//  LREdgeCutShapeView.swift
//  iosApp
//
//  Created by Manuel Baez on 05/06/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct LREdgeCutShapeView: Shape {
    
    let cornerRadius: Float
    
    func path(in rect: CGRect) -> Path {
        return Path { path in
            
            
            path.addArc(
                center: CGPoint(
                    x: rect.minX - CGFloat(cornerRadius),
                    y: rect.midY - CGFloat(cornerRadius)
                ),
                radius: 12.0,
                startAngle: Angle(degrees: 90),
                endAngle: Angle(degrees: -90),
                clockwise: true
            )
            
            path.addLine(to: CGPoint(x: rect.minX, y: rect.midY))
            
            
            
            //            ) lineTo(x = size.width - cornerRadius, y = 0f)
            
            //            path.addArc(center: CGPoint(x: rect.maxX, y: rect.midY),
            //                        radius: 12.0,
            //                        startAngle: Angle(degrees: 270),
            //                        endAngle: Angle(degrees: 90),
            //                        clockwise: true)
        }
    }
}
