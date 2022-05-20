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

    func refreshData() async {
        homeUiState.isLoading = true

        do {
            let sortingMethod = try await getSortingMethod.execute()
            let daysToLotteryDraw = getDaysToLotteryDraw.execute()
            let tickets = try await getTickets.execute()

            homeUiState.daysToLotteryDraw = Int(daysToLotteryDraw)
            homeUiState.tickets = tickets
            homeUiState.isLoading = false
        } catch {
            homeUiState.errorMessages = ["Error"]
        }
    }
}