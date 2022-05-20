import SwiftUI
import shared

struct HomeView: View {
    let greet = Greeting().greeting()

    @StateObject private var viewModel = HomeViewModel()

    var body: some View {
        let uiState = viewModel.homeUiState

        Text("Hole \(uiState.daysToLotteryDraw)")
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}