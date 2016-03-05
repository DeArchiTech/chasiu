package chasiu.Presenter

import chasiu.Model.Model
import chasiu.View.LoginForm
import chasiu.View.SignUpForm
import org.json.JSONObject
import rx.Observable

/**
 * Created by Davix on 3/4/16.
 */

class MainPresenterImpl : MainPresenter{

    override fun getLogInResult(form: LoginForm): Observable<Model.User> {

        //1)Create Rest Adapter
        //2)Make request to get the observable
        //3)Return the observable to caller and let the caller associate it
        //with a subscriber

        throw UnsupportedOperationException()
    }

    override fun getSignUpResult(form: SignUpForm): Observable<Model.User> {
        throw UnsupportedOperationException()
    }

    override fun getUsers(searchParam: JSONObject): Observable<Model.User> {
        throw UnsupportedOperationException()
    }
}