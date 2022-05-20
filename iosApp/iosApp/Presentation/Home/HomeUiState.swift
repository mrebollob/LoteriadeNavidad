import Foundation
import shared

struct HomeUiState {
    var today: Date
    var daysToLotteryDraw: Int
    var totalBet: Float
    var totalPrize: Float?
    var tickets: [Ticket]
    var isLoading: Bool
    var errorMessages: [String]
}