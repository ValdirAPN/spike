package br.com.spike

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import br.com.spike.data.FirebaseAuthentication
import br.com.spike.data.FirebaseFirestore
import br.com.spike.data.buildFirebaseAuthentication
import br.com.spike.data.buildFirebaseFirestore
import br.com.spike.data.createPreferencesDataStore
import br.com.spike.data.repository.AuthRepositoryImpl
import br.com.spike.data.repository.MatchRepositoryImpl
import br.com.spike.domain.repository.AuthRepository
import br.com.spike.domain.repository.MatchRepository
import br.com.spike.presentation.home.homeModule
import br.com.spike.presentation.login.loginModule
import br.com.spike.presentation.matchDetails.matchDetailsModule
import br.com.spike.presentation.matchExplorer.matchExplorerModule
import br.com.spike.presentation.matchForm.matchFormModule
import br.com.spike.presentation.profile.profileModule
import br.com.spike.presentation.signUp.signUpModule
import br.com.spike.presentation.splash.splashModule
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val appModule: DI = DI {

    bindSingleton<FirebaseAuthentication> {
        buildFirebaseAuthentication()
    }

    bindSingleton<FirebaseFirestore> {
        buildFirebaseFirestore()
    }

    bindSingleton<DataStore<Preferences>> {
        createPreferencesDataStore()
    }

    bindSingleton<AuthRepository> {
        AuthRepositoryImpl(
            firebaseAuthentication = instance(),
            firebaseFirestore = instance(),
        )
    }

    bindSingleton<MatchRepository> {
        MatchRepositoryImpl(
            firebaseFirestore = instance(),
            authRepository = instance(),
        )
    }

    import(splashModule)
    import(loginModule)
    import(signUpModule)
    import(homeModule)
    import(profileModule)
    import(matchFormModule)
    import(matchExplorerModule)
    import(matchDetailsModule)
}