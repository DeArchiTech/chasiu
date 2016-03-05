package chasiu.View

import chasiu.Model.Model

/**
 * Created by Davix on 3/4/16.
 */
interface LoginView {

    fun onUserLoginRequest(form : LoginForm)
    //on user triggered login action

    fun onLoginSuccess(): Model.User
    //OnLoginSuccess

    fun onLoginErrpr(): Throwable
    //OnLoginError

}
