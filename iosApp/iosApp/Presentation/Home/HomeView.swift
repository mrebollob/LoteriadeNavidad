import SwiftUI
import shared

struct HomeView: View {
    let greet = Greeting().greeting()

    var body: some View {
        Text(greet)
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}