//
//  HidableSearchTextField.swift
//  iosApp
//
//  Created by Bhavin Matreja on 08/03/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct HidableSearchTextField<Destination:View>: View {
    
    var onSearchToggle: () -> Void
    var destinationProvider: () -> Destination
    var isSearchActive: Bool
    @Binding var searchText: String
    
    var body: some View {
        HStack {
            TextField("Search...", text: $searchText)
                .textFieldStyle(.roundedBorder)
                .opacity(isSearchActive ? 1 : 0)
            if !isSearchActive {
                Spacer()
            }
            Button(action: onSearchToggle) {
                Image(systemName: isSearchActive ? "xmark" : "magnifyingglass")
                    .foregroundColor(.black)
            }
            NavigationLink(destination: destinationProvider) {
                Image(systemName: "plus")
                    .foregroundColor(.black)
            }
        }
    }
}

#Preview {
    HidableSearchTextField(
        onSearchToggle: {},
        destinationProvider: { EmptyView()}, isSearchActive: true, searchText: .constant("YouTube")
    )
}
