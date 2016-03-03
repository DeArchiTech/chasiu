import android.os.Build;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import chasiu.Model.Model;
import chasiu.Network.BackEndService;
import rx.Observable;

/**
 * Created by Davix on 3/2/16.
 */

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP)
public class BackEndImplUnitTest {

    BackEndService service;

    String userID = "0";
    String email = "davidkwokhochan@gmail.com";
    String password = "abcd1234";

    @Test
    public void testConstructor() {

        this.service = BackEndService.Companion.create();
        assert(this.service != null);

    }

    @Test
    public void testSignUpUser() {

        this.service = BackEndService.Companion.create();
        Observable<Model.User> observable = this.service.signUpUser(this.email ,this.password);
        assert(observable != null);

    }

    @Test
    public void testLoginUser() {

        this.service = BackEndService.Companion.create();
        Observable<Model.User> observable = this.service.signUpUser(this.email ,this.password);
        assert(observable != null);
    }

    @Test
    public void testGetHomeScreen() {

        //Test Logged In
        this.service = BackEndService.Companion.create();
        Observable<JSONObject> observable = this.service.getHomeScreen(true);
        assert(observable != null);

        //Test Logged Out
        observable = this.service.getHomeScreen(false);
        assert(observable != null);

    }

    @Test
    public void testGetUserInfo() {

        this.service = BackEndService.Companion.create();
        Observable<Model.User> observable = this.service.getUserInfo(this.userID);
        assert (observable != null);

    }

}