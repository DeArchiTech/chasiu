import android.os.Build;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


/**
 * Created by Davix on 3/4/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP)
public class RestServiceTestHelperTest {

    @Test
    public void testStringHasUpper() {

        //Pass Case
        String inputOne = "Abbb";
        boolean result = RestServiceTestHelper.stringHasUpper(inputOne);
        assert (result == true);

        //Failed Case
        String inputTwo = "bbb";
        result = RestServiceTestHelper.stringHasUpper(inputTwo);
        assert (result == false);

        //Corner Case
        String inputThree = null;
        result = RestServiceTestHelper.stringHasUpper(inputThree);
        assert (result == false);

        //Weird case
        String inputFour = new StringBuffer().toString();
        result = RestServiceTestHelper.stringHasUpper(inputFour);
        assert (result == false);

    }

}





