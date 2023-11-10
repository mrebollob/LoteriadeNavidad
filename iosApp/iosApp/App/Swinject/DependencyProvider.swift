import Swinject

public class DependencyProvider {

    public static let shared: DependencyProvider = DependencyProvider()
    private let container: Container
    private let assembler: Assembler

    public static var resolver: Resolver {
        Self.shared.assembler.resolver
    }

    private init() {
        self.container = Container()
        self.assembler = Assembler(container: self.container)
    }

    public func apply(assemblies: [Assembly]) {
        assembler.apply(assemblies: assemblies)
    }

    public func apply(assembly: Assembly) {
        assembler.apply(assembly: assembly)
    }
}