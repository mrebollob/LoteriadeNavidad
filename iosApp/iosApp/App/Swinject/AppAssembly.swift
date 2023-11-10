import Foundation
import Swinject

class AppAssembly: Assembly {

    func assemble(container: Container) {

        _ = Assembler(
                [
                    UseCaseAssembly()
                ],
                container: container)
    }
}