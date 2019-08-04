package ravinder.example.myklan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class MembersEmpty extends AppCompatActivity {
    private TextView text1;
    String authenticator="";
    String uid="";
    String str="";
    private Button aboutButton;
    private final String tag = "About BUTTON CALL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_empty);
        Intent intent = getIntent();
        String message = intent.getStringExtra("familyName");

        authenticator = intent.getStringExtra("Authorization");
        uid = intent.getStringExtra("UserId");

        aboutButton = findViewById(R.id.aboutButton);

        text1 = (TextView) findViewById(R.id.labelFamilyName);
        text1.setText(message);
         Log.e("userID",""+uid);


        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetFamilyMembers(uid);

            }
        });


    }
//public void addMemberClick(View view) {
//
//
//
//}

    public void GetFamilyMembers(final String uid)
    {
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                HttpsURLConnection connection = null;
                try {
                    //{"key":"Authorization","value":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAbXlrbGFuLmNvbSIsImlhdCI6MTU2MjcxMjc4MCwiZXhwIjoxNTYyNzk5MTgwfQ.IU9uNrzoiFYgkwp7b4MCWkHPIoCdGP0PIgnGBktmZ5I","description":"","type":"text","enabled":true}
                    String url = "https://w4dtt62bhd.execute-api.us-east-1.amazonaws.com/dev/getMembers?userId="+uid;



                    Log.e("url",url);


                    URL loginEndPoint = new URL(url);
                    connection = (HttpsURLConnection) loginEndPoint.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Authorization",authenticator);

                    //connection.setRequestProperty("key","Authorization");
                    //connection.setRequestProperty("value",authenticator);
                    //connection.setRequestProperty("type","text");
                    //connection.setRequestProperty("enabled","true");
                    //connection.setRequestProperty("description","");
                    //Log.e("test",connection.getHeaderField("value"));
                    //connection.setRequestProperty("value","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAbXlrbGFuLmNvbSIsImlhdCI6MTU2MjcxMjc4MCwiZXhwIjoxNTYyNzk5MTgwfQ.IU9uNrzoiFYgkwp7b4MCWkHPIoCdGP0PIgnGBktmZ5I");
                    Log.e(tag,"Authorization code " + connection.getRequestProperty("Authorization"));

                    // Get Response
                    String responseCode = String.valueOf(connection.getResponseCode());
                    Log.e(tag,"the response: " + responseCode);
                    InputStream is = connection.getInputStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer response = new StringBuffer();
                    while ((line = rd.readLine()) != null) {
                        response.append(line);
                        response.append('\r');
                    }
                    rd.close();
                    Log.e(tag,"the response: " + response);
                    str=response.toString();

                    JSONArray jsonarray = new JSONArray(str);
                    for(int i=0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        String name = jsonobject.getString("name");
                        Log.e(tag, "Name: " + name);
                    }


                    SharedPreferences mPrefs = getSharedPreferences("IDvalue", 0);
                   //Give any name for //preference as I have given "IDvalue" and value 0.
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putString("str", str);
                   // give key value as "sound" you mentioned and value what you // to want give as "1" in you mentioned
                    editor.commit();





                    Intent whoAreYou = new Intent(getApplicationContext(), whoAreYou.class);
                    //whoAreYou .putExtra("familyMembers", str);
                    startActivity(whoAreYou);



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        });



    }

}