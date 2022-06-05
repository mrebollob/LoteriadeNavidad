import SwiftUI
import shared

struct HomeView: View {
    let greet = Greeting().greeting()
    
    @StateObject private var viewModel = HomeViewModel()
    
    var body: some View {
        let uiState = viewModel.uiState
        NavigationView {
            ScrollView{
                VStack {
                    Spacer()
                    
                    CountdownSectionView(
                        today: uiState.today,
                        daysToLotteryDraw: uiState.daysToLotteryDraw
                    )
                    
                    Spacer()
                        .frame(height: 24)
                    
                    StatsSectionView(
                        totalBet: 20,
                        totalPrize: nil
                    )
                    
                    Spacer()
                        .frame(height: 24)
                    
                    TicketsListView(tickets: uiState.tickets)
                }
            }
            .onAppear(perform: viewModel.refreshData)
            .navigationTitle("Lotería 2022")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    NavigationLink(destination: CreateScreen()) {
                        Image(systemName: "plus")
                            .foregroundColor(Color("PrimaryColor"))
                    }
                }
                
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button(action: {
                        
                    }, label: {
                        Image(systemName: "gearshape")
                            .foregroundColor(Color("OnBackground"))
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
