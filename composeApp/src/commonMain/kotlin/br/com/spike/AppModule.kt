package br.com.spike

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import br.com.spike.data.FirebaseAuthentication
import br.com.spike.data.FirebaseFirestore
import br.com.spike.data.buildFirebaseAuthentication
import br.com.spike.data.buildFirebaseFirestore
import br.com.spike.data.createPreferencesDataStore
import br.com.spike.data.service.AuthServiceImpl
import br.com.spike.domain.service.AuthService
import br.com.spike.presentation.login.loginModule
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

    bindSingleton<AuthService> {
        AuthServiceImpl(
            firebaseAuthentication = instance(),
            firebaseFirestore = instance(),
        )
    }

    import(splashModule)
    import(loginModule)
    import(profileModule)
    import(signUpModule)
}