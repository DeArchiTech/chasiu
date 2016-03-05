package chasiu.Presenter
import chasiu.View.LoginView

/**
 * Created by Davix on 3/4/16.
 */

class LoginPresenterImpl : LoginPresenter {

    private var view: LoginView? = null
    private var items : Any? = null;
    private var error: Throwable? = null;

    override fun onTakeView(view: LoginView) {

        this.view = view;
        publish();
        throw UnsupportedOperationException()
    }

    private fun publish() {

        if (this.view != null) {

            if (this.items != null) {
                this.view?.onLoginSuccess();
            } else if (this.error != null) {
                this.view?.onLoginErrpr();
            }

        }
    }

}