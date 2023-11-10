import Foundation
import Swinject
import shared

class UseCaseAssembly: Assembly {

    func assemble(container: Container) {

        // SettingsRepository
        container.register(SettingsRepository.self) { r in
                    SettingsRepositoryImp()
                }
                .inObjectScope(.container)

        // SettingsRepository
        container.register(TicketsRepository.self) { r in
                    TicketsRepositoryImp()
                }
                .inObjectScope(.container)


        // GetDaysToLotteryDraw
        container.register(GetDaysToLotteryDraw.self) { r in
                    GetDaysToLotteryDraw()
                }
                .inObjectScope(.container)

        // CreateTicket
        container.register(CreateTicket.self) { r in
                    CreateTicket(
                            ticketsRepository: r.resolve(TicketsRepository.self)!
                            )
                }
                .inObjectScope(.container)

        // GetDaysToLotteryDraw
        container.register(DeleteTicket.self) { r in
                    DeleteTicket(
                            ticketsRepository: r.resolve(TicketsRepository.self)!
                            )
                }
                .inObjectScope(.container)

        // GetDaysToLotteryDraw
        container.register(GetTickets.self) { r in
                    GetTickets(
                            ticketsRepository: r.resolve(TicketsRepository.self)!
                            )
                }
                .inObjectScope(.container)

        // GetSortingMethod
        container.register(GetSortingMethod.self) { r in
                    GetSortingMethod(
                            settingsRepository: r.resolve(SettingsRepository.self)!
                            )
                }
                .inObjectScope(.container)

        // SaveSortingMethod
        container.register(SaveSortingMethod.self) { r in
                    SaveSortingMethod(
                            settingsRepository: r.resolve(SettingsRepository.self)!
                            )
                }
                .inObjectScope(.container)
    }
}