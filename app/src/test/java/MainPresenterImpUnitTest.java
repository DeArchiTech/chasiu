import android.graphics.AvoidXfermode;
import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;

import chasiu.Model.Model;
import chasiu.Network.BackEndService;
import chasiu.Network.MockBackEndService;
import chasiu.Presenter.MainPresenter;
import chasiu.Presenter.MainPresenterImpl;
import chasiu.View.LoginForm;
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
    public void testGetLoginResult(){

        //Set Up
        BackEndService sampleService = new MockBackEndService();
        BackEndService presenterService = new MockBackEndService();

        MainPresenter presenter = new MainPresenterImpl();
        LoginForm form = new LoginForm(this.email , this.password);
        Observable<Model.User> observable = presenter.getLogInResult(form);
        TestSubscriber<Model.User> testSubscriber = new TestSubscriber<Model.User>();

        //Test Observables
        assert(observable != null);
        observable.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();

        //Test The object thats emitted
        Model.User firstResult = sampleService.getUserInfo("0").toBlocking().first();
        testSubscriber.assertReceivedOnNext(Arrays.asList(firstResult));

    }

    @Test
    public void testSignUpResult(){

        assert(false);

    }

    @Test
    public void testGetUsers(){

        assert(false);

    }

}
