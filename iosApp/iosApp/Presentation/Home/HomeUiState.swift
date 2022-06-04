import Foundation
import shared

struct HomeUiState {
    var today: Date =  Date()
    var daysToLotteryDraw: Int = 0
    var totalBet: Float = 0
    var totalPrize: Float? = nil
    var tickets: [Ticket] = []
    var isLoading: Bool = false
    var errorMessages: [String] = []
}
