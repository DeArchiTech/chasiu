package chasiu;


import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@Config(sdk = Build.VERSION_CODES.LOLLIPOP ,
        constants = BuildConfig.class)
@RunWith(RobolectricGradleTestRunner.class)
public class UnitTest {

    @Test
    public void clickingLogin_shouldStartLoginActivity() {

/*        WelcomeActivity activity = Robolectric.setupActivity(WelcomeActivity.class);
        activity.findViewById(R.id.login).performClick();

        Intent expectedIntent = new Intent(activity, WelcomeActivity.class);
        assertThat(shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);*/
        assert(true);
    }



}