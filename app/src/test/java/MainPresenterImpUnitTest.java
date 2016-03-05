import android.os.Build;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;

import chasiu.Model.Model;
import chasiu.Network.BackEndService;
import chasiu.Network.MockBackEndService;
import chasiu.Presenter.LoginPresenter;
import chasiu.Presenter.LoginPresenterImpl;
import chasiu.View.LoginForm;
import chasiu.View.LoginView;
import rx.Observable;
import rx.observers.TestSubscriber;

/**
 * Created by Davix on 3/4/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP)
public class MainPresenterImpUnitTest {

    String userID = "0";
    String username = "david";
    String email = "davidkwokhochan@gmail.com";
    String password = "abcd1234";

    @Test
    public void testOnTakeView(){

        //Set Up
        LoginPresenter presenter = new LoginPresenterImpl();
        LoginView mockLoginView = new LoginView() {

            @Override
            public void onUserLoginRequest(@NotNull LoginForm form) {

            }

            @NotNull
            @Override
            public Model.User onLoginSuccess() {
                return null;
            }

            @NotNull
            @Override
            public Throwable onLoginErrpr() {
                return null;
            }
        };

        //Trigger action and don't break
        presenter.onTakeView(mockLoginView);
        LoginForm loginForm = new LoginForm(this.email , this.password);
        mockLoginView.onUserLoginRequest(loginForm);

    }

}
