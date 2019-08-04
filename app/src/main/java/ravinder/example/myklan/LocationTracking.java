package ravinder.example.myklan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LocationTracking extends AppCompatActivity {
    private final String tag = "BUTTON CALL";
    String memberId="";
    String authenticator="";
    private TextView location1;
    private TextView latitude1;
    private TextView longitude1;
String latitude="";
String longitude="";
String lastUpdate="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_tracking);
        SharedPreferences mPrefs = getSharedPreferences("authentication", 0);
        authenticator = mPrefs.getString("authenticator", "");
        location1= (TextView) findViewById(R.id.location);
        latitude1 = (TextView) findViewById(R.id.latitude);
        longitude1 = (TextView) findViewById(R.id.longitude);

        Intent intent = getIntent();
        String pos = intent.getStringExtra("quantity");
        int position=Integer.parseInt(pos);

        SharedPreferences Prefs = getSharedPreferences("IDvalue",0);
        String familyMembers = Prefs.getString("str", "");

        try {
            JSONArray jsonarray = new JSONArray(familyMembers);

            JSONObject jsonobject = jsonarray.getJSONObject(position);
             memberId = jsonobject.getString("_id");
            Log.e("memberId", memberId);



        } catch (
                JSONException e) {
            e.printStackTrace();
        }


        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                HttpsURLConnection connection = null;
                try {
                    String url = "https://w4dtt62bhd.execute-api.us-east-1.amazonaws.com/dev/locationTracking/getLocation?memberId="+ memberId;

                    Log.e("url", url);


                    URL loginEndPoint = new URL(url);
                    connection = (HttpsURLConnection) loginEndPoint.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Authorization", authenticator);

                    Log.e(tag, "Authorization code " + connection.getRequestProperty("Authorization"));

                    // Get Response
                    String responseCode = String.valueOf(connection.getResponseCode());
                    Log.e(tag, "the response: " + responseCode);
                    InputStream is = connection.getInputStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer response = new StringBuffer();
                    while ((line = rd.readLine()) != null) {
                        response.append(line);
                        response.append('\r');
                    }
                    rd.close();
                    Log.e(tag, "Location Info: " + response);
                    String location = response.toString();

                    try {

                            JSONObject jsonobject = new JSONObject(location);
                     latitude = jsonobject.getString("latitude");
                     longitude = jsonobject.getString("longitude");
                     lastUpdate=jsonobject.getString("lastLocationUpdate");
                            Log.e("latitude",latitude);
                            Log.e("longitude",longitude);





                    } catch (
                            JSONException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
                latitude1.setText(latitude);
                longitude1.setText(longitude);
                location1.setText(lastUpdate);

               
            }
        });




    }
}
