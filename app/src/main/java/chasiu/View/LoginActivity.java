package chasiu.View;

import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import chasiu.Form.LoginForm;
import chasiu.Model.Model;
import chasiu.Presenter.LoginPresenter;
import chasiu.Presenter.LoginPresenterImpl;
import chasiu.R;

public class LoginActivity extends AppCompatActivity implements LoginView {

	private Toolbar toolbar;
	private DrawerLayout drawerLayout;
	private LoginForm loginForm = null;
	private ActionBarDrawerToggle drawerToggle;
	private LoginPresenter presenter;
	public boolean loginResponseRecieved = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (toolbar != null) {
			toolbar.setTitle(R.string.app_name);
			setSupportActionBar(toolbar);
		}
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}
		};
		drawerLayout.setDrawerListener(drawerToggle);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment())
					.commit();
		}

		this.presenter = new LoginPresenterImpl();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_main, container, false);
		}
	}

	@NotNull
	@Override
	public void onLoginSuccess(Model.User user){
		//TODO
		//Display a message to user
		this.loginResponseRecieved = true;
	}

	@NotNull
	@Override
	public void onLoginError(Throwable e){
		//TODO
		//Display a message to user
		this.loginResponseRecieved = true;

	}

	@Override
	public void onUserLoginRequest() {
		//Call presenter to handle this user request
		this.presenter.onUserEvent(new Object());
	}

	@NotNull
	@Override
	public LoginForm getUserLoginInput() {

		if(this.loginForm!=null){
			return this.loginForm;
		}else{
			return new LoginForm("" , "");
		}

	}

	public LoginPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(LoginPresenter presenter) {
		this.presenter = presenter;
	}

}
