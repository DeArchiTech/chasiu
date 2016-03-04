package chasiu.Network;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import chasiu.Model.Model;
import retrofit.http.Field;
import rx.Observable;

/**
 * Created by Davix on 3/4/16.
 */
public class MockBackEndService implements BackEndService{

    public MockBackEndService() {
    }

    @NotNull
    @Override
    public Observable<Model.User> signUpUser(@Field("username") @NotNull String name, @Field("email") @NotNull String email) {
        return Observable.just(getBaseUser());
    }

    @NotNull
    @Override
    public Observable<Model.User> logInUser(@Field("username") @NotNull String name, @Field("email") @NotNull String email) {
        return Observable.just(getBaseUser());
    }

    @NotNull
    @Override
    public Observable<Model.User> getUserInfo(@Field("userId") @NotNull String id) {
        return Observable.just(getBaseUser());
    }

    @NotNull
    @Override
    public Observable<Model.User[]> getUsers(@Field("QueryParam") @NotNull JSONObject loggedIn) {

        Model.User[] users = new Model.User[2];
        users[0] = getBaseUser();
        return Observable.just(users);

    }

    private Model.User getBaseUser(){

        return new Model.User("david" , "0");

    }
}
