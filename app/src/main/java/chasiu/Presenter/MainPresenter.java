package chasiu.Presenter;

import chasiu.Model.Model;
import rx.Observable;

/**
 * Created by Davix on 3/4/16.
 */
public interface MainPresenter {

    //Called by View
    //It returns an observable stream back to the view
    //The view than must implement how the observable stream
    //wanted to be handled

    public Observable<Model.User> getUserInfo();





}
