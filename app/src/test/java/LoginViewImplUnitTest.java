import android.os.Build;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import chasiu.Model.Model;
import chasiu.Presenter.LoginPresenter;
import chasiu.View.LoginActivity;
import chasiu.View.LoginView;
import rx.Observable;

/**
 * Created by Davix on 3/4/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP , manifest = "src/main/AndroidManifest.xml")
public class LoginViewImplUnitTest {

    LoginActivity view ;
    LoginPresenter presenter;
    boolean eventTriggered = false;

    @Before
    public void setUp(){

        //TODO Refactor
        //Null pointer exception
        //this.view = Robolectric.setupActivity(LoginActivity.class);
        this.view = new LoginActivity();
        this.presenter=new LoginPresenter() {
            @Override
            public void onTakeView(@NotNull LoginView view) {

            }

            @Nullable
            @Override
            public Observable<Model.User> onUserEvent(@NotNull Object event) {
                LoginViewImplUnitTest.this.eventTriggered = true;
                return null;
            }
        };
        this.view.setPresenter(this.presenter);
    }

    @Test
    public void testOnLoginSuccess(){

        this.view.onLoginSuccess(new Model.User("",""));
        assert(this.view.loginResponseRecieved);
    }

    @Test
    public void testOnLoginError(){

        this.view.onLoginError(new Throwable());
        assert(this.view.loginResponseRecieved);
    }

    @Test
    public void testOnUserLoginRequest(){

        this.view.onUserLoginRequest();
        assert(this.eventTriggered == true);

    }

    @Test
    public void testGetUserLoginInput(){

        assert(this.view.getUserLoginInput() != null );

    }
}
