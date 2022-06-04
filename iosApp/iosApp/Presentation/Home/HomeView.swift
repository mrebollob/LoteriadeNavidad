import SwiftUI
import shared

struct HomeView: View {
    let greet = Greeting().greeting()

    @StateObject private var viewModel = HomeViewModel()

    var body: some View {
        let uiState = viewModel.uiState
        NavigationView {
            VStack {
                Spacer()
                
                CountdownSectionView(
                    today: uiState.today,
                    daysToLotteryDraw: uiState.daysToLotteryDraw
                )
                
                Spacer()
                
                TicketsListView(tickets: uiState.tickets)
            }
                    .onAppear(perform: viewModel.refreshData)
                    .navigationTitle("Lotería 2022")
                    .navigationBarTitleDisplayMode(.inline)
                    .toolbar {
                        ToolbarItem(placement: .navigationBarTrailing) {
                            NavigationLink(destination: CreateScreen()) {
                                Image(systemName: "plus")
                            }
                        }

                        ToolbarItem(placement: .navigationBarTrailing) {
                            Button(action: {

                            }, label: {
                                Image(systemName: "gearshape")
                            })
                        }
                    }
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
