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

    private var fetchPeopleTask: Task<(), Never>? = nil

    @Published var homeUiState: HomeUiState = HomeUiState(
            today: Date(),
            daysToLotteryDraw: 0,
            totalBet: 0,
            totalPrize: 0,
            tickets: [],
            isLoading: false,
            errorMessages: []
    )

    func refreshData() {
        homeUiState.isLoading = true
        let daysToLotteryDraw = getDaysToLotteryDraw.execute()
        homeUiState.daysToLotteryDraw = Int(daysToLotteryDraw)

        getTickets.execute { (tickets: [Ticket]?, error: Error?) in

        }
    }
}