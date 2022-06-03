//
// Created by Manuel Baez on 03/06/2022.
// Copyright (c) 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CreateScreen: View {

    @Environment(\.presentationMode) var presentationMode

    @StateObject private var viewModel = CreateViewModel()
    @State var name: String = ""
    @State var number: String = ""
    @State var bet: String = ""

    var body: some View {
        VStack(alignment: .leading) {
            Text("Nombre")
                    .font(.callout)
                    .bold()
            TextField("", text: $name)
                    .textFieldStyle(RoundedBorderTextFieldStyle())

            Text("Número")
                    .font(.callout)
                    .bold()
                    .padding(EdgeInsets(top: 16, leading: 0, bottom: 0, trailing: 0))

            TextField("", text: $number)
                    .textFieldStyle(RoundedBorderTextFieldStyle())

            Text("Jugado (€)")
                    .font(.callout)
                    .bold()
                    .padding(EdgeInsets(top: 16, leading: 0, bottom: 0, trailing: 0))
            TextField("", text: $bet)
                    .textFieldStyle(RoundedBorderTextFieldStyle())

            Button(action: {
                viewModel.onSaveTicketClick(name: name, number: number, bet: bet)
            }) {
                HStack {
                    Text("Guardar cupón")
                            .fontWeight(.semibold)
                }
                        .frame(minWidth: 0, maxWidth: .infinity)
                        .padding()
                        .foregroundColor(.white)
                        .background(Color.red)
                        .cornerRadius(40)
            }
                    .padding(EdgeInsets(top: 24, leading: 0, bottom: 0, trailing: 0))
        }
                .onReceive(viewModel.$uiState) { uiState in
                    if (uiState.isCreated) {
                        presentationMode.wrappedValue.dismiss()
                    }
                }
                .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
                .padding()
                .navigationTitle("Nuevo cupón")
                .navigationBarTitleDisplayMode(.inline)
    }
}

struct CreateScreen_Previews: PreviewProvider {
    static var previews: some View {
        CreateScreen()
    }
}
