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
    @State private var showAlert = false
    
    var body: some View {
        VStack(alignment: .leading) {
            Text("Nombre")
                .font(.callout)
                .bold()
            TextField("", text: $name)
                .autocapitalization(UITextAutocapitalizationType.sentences)
                .textFieldStyle(RoundedBorderTextFieldStyle())
            
            Text("Número")
                .font(.callout)
                .bold()
                .padding(EdgeInsets(top: 16, leading: 0, bottom: 0, trailing: 0))
            
            TextField("", text: $number)
                .keyboardType(.numberPad)
                .textFieldStyle(RoundedBorderTextFieldStyle())
            
            Text("Jugado (€)")
                .font(.callout)
                .bold()
                .padding(EdgeInsets(top: 16, leading: 0, bottom: 0, trailing: 0))
            TextField("", text: $bet)
                .keyboardType(.decimalPad)
                .textFieldStyle(RoundedBorderTextFieldStyle())
            
            Button(action: {
                if viewModel.uiState.showError == false {
                    viewModel.onSaveTicketClick(
                        name: name,
                        numberText: number,
                        betText: bet
                    )
                }
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
            .alert(isPresented: $viewModel.uiState.showError) {
                Alert(
                    title: Text("Error al guardar el cupón"),
                    message: Text(viewModel.uiState.errorMessages),
                    dismissButton: .default(Text("OK"))
                )
                
            }
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
