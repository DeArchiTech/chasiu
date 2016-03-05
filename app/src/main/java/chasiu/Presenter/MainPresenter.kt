package chasiu.Presenter

import org.json.JSONObject

import chasiu.Model.Model
import chasiu.View.LoginForm
import chasiu.View.SignUpForm
import rx.Observable

/**
 * Created by Davix on 3/4/16.
 */
interface MainPresenter{

    //Called by View
    //It returns an observable stream back to the view
    //The view than must implement how the observable stream
    //wanted to be handled

    fun getLogInResult(form: LoginForm): Observable<Model.User>

    fun getSignUpResult(form: SignUpForm): Observable<Model.User>

    fun getUsers(searchParam: JSONObject): Observable<Model.User>

}
