import SwiftUI
import shared

struct HomeView: View {
    let greet = Greeting().greeting()

    @StateObject private var viewModel = HomeViewModel()

    var body: some View {
        let uiState = viewModel.homeUiState
        ScrollView {
            Text("Dias \(uiState.daysToLotteryDraw)")
        }
                .onAppear(perform: viewModel.refreshData)
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}