package com.example.techmarket.core.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.techmarket.latech.domain.model.Role
import com.example.techmarket.latech.domain.model.Role.Admin
import com.example.techmarket.latech.domain.model.Role.Customer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class AppPreferencesDataSource(private val context: Context) {

    // write
    suspend fun saveLoginState(isLoggedIn: Boolean) {
        context.appDateStore.edit {
            it[DataStoreKeys.IS_USER_LOGGED_IN] = isLoggedIn
        }
    }

    suspend fun saveRoleState(role: String?) {
        context.appDateStore.edit {
            when(role){
                Customer -> it[DataStoreKeys.USER_ROLE] = Customer
                Admin -> it[DataStoreKeys.USER_ROLE] =  Admin
            }
        }
    }


    // read
    val isUserLoggedIn: Flow<Boolean> = context.appDateStore.data
        .map {
            it[DataStoreKeys.IS_USER_LOGGED_IN] ?: false
        }

    val userRole: Flow<String> = context.appDateStore.data.map {
        it[DataStoreKeys.USER_ROLE] ?: Admin
    }

}