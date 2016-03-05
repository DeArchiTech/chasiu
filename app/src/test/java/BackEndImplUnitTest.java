import android.content.Context;
import android.os.Build;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.stubbing.answers.ThrowsException;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import chasiu.Model.Model;
import chasiu.Network.BackEndService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import rx.Observable;
import rx.Subscriber;
import rx.observers.TestSubscriber;

/**
 * Created by Davix on 3/2/16.
 */

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP)
public class BackEndImplUnitTest {

    BackEndService service;
    MockWebServer server;

    String userID = "0";
    String username = "david";
    String email = "davidkwokhochan@gmail.com";
    String password = "abcd1234";

    @Test
    public void testConstructor() {

        this.service = BackEndService.Companion.create();
        assert(this.service != null);

    }

    @Test
    public void testSignUpUser() throws InterruptedException{

        //Set Up
        this.service = BackEndService.Companion.create();
        this.server = new MockWebServer();

        final CountDownLatch signal = new CountDownLatch(1);
        Model.User user = new Model.User(this.username , this.password);
        ObjectMapper mapper = new ObjectMapper();
        String responseString = "";

        try{

            responseString = mapper.writeValueAsString(user);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

        //Enqueue Server Response
        this.server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseString));

        Observable<Model.User> observable = this.service.signUpUser(this.username ,this.password);
        TestSubscriber<Model.User> testSubscriber = new TestSubscriber<Model.User>();

        //Test Observable
        assert(observable != null);

        observable.subscribe(testSubscriber);

        //Test Observable results
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertReceivedOnNext(Arrays.asList(user));
    }

    @Test
    public void testLoginUser() {

        this.service = BackEndService.Companion.create();
        Observable<Model.User> observable = this.service.signUpUser(this.email ,this.password);
        assert(observable != null);

        Model.User user = null;

        assert(user!=null);

    }

    @Test
    public void testGetUsers() {

        //Test Logged In
        this.service = BackEndService.Companion.create();
        Observable<Model.User[]> observable= this.service.getUsers(new JSONObject());
        assert(observable != null);

    }

    @Test
    public void testGetUserInfo() {

        this.service = BackEndService.Companion.create();
        Observable<Model.User> observable = this.service.getUserInfo(this.userID);
        assert (observable != null);

        Model.User user = null;

        assert(user!=null);
    }

}