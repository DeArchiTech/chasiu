import android.os.Build;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import chasiu.Model.Model;
import chasiu.Network.BackEndService;
import chasiu.Network.MockBackEndService;
import chasiu.Presenter.LoginPresenterImpl;
import chasiu.Form.LoginForm;
import chasiu.View.LoginView;
import rx.Observable;
import rx.observers.TestSubscriber;

/**
 * Created by Davix on 3/4/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP)
public class LoginPresenterImpUnitTest {

    String userID = "0";
    String username = "david";
    String email = "davidkwokhochan@gmail.com";
    String password = "abcd1234";
    LoginView mockLoginView = null;
    BackEndService mockBackendService = null;
    LoginPresenterImpl presenter = null;

    @Before
    public void setUp(){

        mockLoginView = new LoginView() {
            @Override
            public void onUserLoginRequest() {

            }

            @Override
            public void onLoginSuccess(@NotNull Model.User user) {

            }

            @Override
            public void onLoginError(@NotNull Throwable error) {

            }

            @NotNull
            @Override
            public LoginForm getUserLoginInput() {
                return new LoginForm("david" , "0");
            }

        };
        mockBackendService = new MockBackEndService();
        presenter = new LoginPresenterImpl();
        presenter.setBackEndService(mockBackendService);

    }

    @Test
    public void testConstructor(){

        assert(presenter != null);

    }

    @Test
    public void testOnTakeView(){

        //Trigger action and don't break
        presenter.onTakeView(mockLoginView);
        LoginForm loginForm = new LoginForm(this.email , this.password);
        mockLoginView.onUserLoginRequest();
        assert(presenter!=null);
    }

    @Test
    public void testOnUserEvent(){

        presenter.onTakeView(mockLoginView);
        Object event = new Object();
        Observable<Model.User> observable = presenter.onUserEvent(event);
        TestSubscriber<Model.User> subscriber = new TestSubscriber<Model.User>();
        observable.subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }
}
