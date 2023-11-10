import UIKit
import shared
import Firebase
import Foundation
import Swinject

class AppDelegate: UIResponder, UIApplicationDelegate {

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil) -> Bool {
        DependencyProvider.shared.apply(assembly: AppAssembly())
        FirebaseApp.configure()
        KoinKt.doInitKoin()
        return true
    }
}
