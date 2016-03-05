package chasiu.Presenter
import chasiu.Model.Model
import chasiu.Network.BackEndService
import chasiu.Form.LoginForm
import chasiu.View.LoginView
import rx.Observable
import rx.Subscriber

/**
 * Created by Davix on 3/4/16.
 */

class LoginPresenterImpl : LoginPresenter {

    private var view: LoginView? = null
    private var items : Model.User? = null
    private var error: Throwable? = null
    var backEndService: BackEndService? = null
    var published: Boolean = false

    override fun onTakeView(view: LoginView) {

        this.view = view;
        publish();
    }

    private fun publish() {

        if (this.view != null) {

            if (this.items != null) {
                this.view?.onLoginSuccess(this.items!!);
            } else if (this.error != null) {
                this.view?.onLoginError(this.error!!);
            }
            published = true
        }
    }

    override fun onUserEvent(event: Any): Observable<Model.User>? {

        if(this.backEndService != null && this.view != null){

            var loginForm : LoginForm = this.view!!.getUserLoginInput()
            var observable : Observable<Model.User> = this.backEndService!!.logInUser(loginForm.email, loginForm.password);
            var subscriber : Subscriber<Model.User> = (object:Subscriber<Model.User>(){
                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                    error = e
                    publish()
                }

                override fun onNext(t: Model.User?) {
                    items = t
                    publish()
                }
            })

            observable.subscribe(subscriber)
            return observable;
        }
        return null;

    }
}