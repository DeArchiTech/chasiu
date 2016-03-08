import android.os.Build;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import chasiu.Model.Model;
import chasiu.Network.BackEndService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import rx.Observable;
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

    MockResponse userMockResponse;
    Model.User user;
    @Before
    public void setUp(){

        this.service = BackEndService.Companion.create();
        this.server = new MockWebServer();
        this.user = new Model.User(this.username , this.password);
        this.userMockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getJsonString(user));

    }

    @Test
    public void testConstructor() {

        assert(this.service != null);

    }

    @Test
    public void testSignUpUser() throws InterruptedException{

        //Enqueue Server Response
        this.server.enqueue(this.userMockResponse);

        Observable<Model.User> observable = this.service.signUpUser(this.username ,this.password);
        TestSubscriber<Model.User> testSubscriber = new TestSubscriber<Model.User>();

        //Test Observable
        assert(observable != null);
        observable.subscribe(testSubscriber);

        //Test Observable results
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertReceivedOnNext(Arrays.asList(this.user));
    }

    @Test
    public void testLoginUser() {

        //Enqueue Server Response
        this.server.enqueue(this.userMockResponse);

        Observable<Model.User> observable = this.service.logInUser(this.username, this.password);
        TestSubscriber<Model.User> testSubscriber = new TestSubscriber<Model.User>();

        //Test Observable
        assert(observable != null);
        observable.subscribe(testSubscriber);

        //Test Observable results
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertReceivedOnNext(Arrays.asList(this.user));

    }

    @Test
    public void testGetUserInfo() {

        //Enqueue Server Response
        this.server.enqueue(this.userMockResponse);
        Observable<Model.User> observable = this.service.getUserInfo("");
        TestSubscriber<Model.User> testSubscriber = new TestSubscriber<Model.User>();

        //Test Observable
        assert(observable != null);
        observable.subscribe(testSubscriber);

        //Desired Emitted result
        List<Model.User> listOfUsers = new ArrayList<Model.User>();
        listOfUsers.add(this.user);

        //Test Observable results
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertValueCount(1);
    }

    @Test
    public void testGetUsers() {

        //Enqueue Server Response
        //TODO: Add A List of Users User To MockResponse
        this.server.enqueue(this.userMockResponse);
        JSONObject searchParm = null;

        try{
            searchParm = new JSONObject();
            searchParm.put("id" , "0");
        }catch (Exception e){

        }
        Observable<List<Model.User>> observable = this.service.getUsers(searchParm);
        TestSubscriber<List<Model.User>> testSubscriber = new TestSubscriber<List<Model.User>>();

        //Test Observable
        assert(observable != null);
        observable.subscribe(testSubscriber);

        //Desired Emitted result
        List<Model.User> listOfUsers = new ArrayList<Model.User>();
        listOfUsers.add(this.user);

        //Test Observable results
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertReceivedOnNext(Arrays.asList(listOfUsers));
    }

}