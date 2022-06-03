import Foundation
import Promises
import shared
import KMPNativeCoroutinesAsync

class HomeViewModel: ObservableObject {

    @ForceInject<GetDaysToLotteryDraw>()
    var getDaysToLotteryDraw: GetDaysToLotteryDraw
    @ForceInject<GetTickets>()
    var getTickets: GetTickets
    @ForceInject<GetSortingMethod>()
    var getSortingMethod: GetSortingMethod

    @Published var uiState: HomeUiState = HomeUiState(
            today: Date(),
            daysToLotteryDraw: 0,
            totalBet: 0,
            totalPrize: 0,
            tickets: [],
            isLoading: false,
            errorMessages: []
    )

    func refreshData() {
        uiState.isLoading = true
        let daysToLotteryDraw = getDaysToLotteryDraw.execute()
        uiState.daysToLotteryDraw = Int(daysToLotteryDraw)

        getTickets.execute { (tickets: [Ticket]?, error: Error?) in

        }
    }
}