package chasiu.View

import chasiu.Form.LoginForm
import chasiu.Model.Model

/**
 * Created by Davix on 3/4/16.
 */
interface LoginView {

    fun onUserLoginRequest()
    //on user triggered login action

    fun onLoginSuccess(user : Model.User)
    //OnLoginSuccess

    fun onLoginError(error : Throwable)
    //OnLoginError

    fun getUserLoginInput(): LoginForm
    //Get User's Inputed Login Data

}
