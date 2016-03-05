/**
 * Created by Davix on 3/3/16.
 */
import android.content.Context;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author rebeccafranks
 * @since 15/10/24.
 */
public class RestServiceTestHelper {

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static String getStringFromFile(Context context, String filePath) throws Exception {
        final InputStream stream = context.getResources().getAssets().open(filePath);

        String ret = convertStreamToString(stream);
        //Make sure you close all streams.
        stream.close();
        return ret;
    }

    public static boolean stringHasUpper(String input){

        //Error Case
        if(input == null || input.length()<1){
            return false;
        }
        else{

            //Algo convert first char to ACSII and see if it lies in the interval if we convert
            //A and Z to its ACSII equivalent
            int inputACSII = input.charAt(0);
            int beginningInterval = 'A';
            int endInterval = 'Z';

            //Base case
            if(inputACSII >= beginningInterval && inputACSII <= endInterval){
                return true;
            }else{
                //Recursive Case
                return stringHasUpper(input.substring(1));
            }

        }

    }

    public static String getJsonString(Object object){
        ObjectMapper mapper = new ObjectMapper();
        String responseString = "";

        try{

            responseString = mapper.writeValueAsString(object);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

        return responseString;
    }

}
