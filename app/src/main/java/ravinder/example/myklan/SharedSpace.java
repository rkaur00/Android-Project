package ravinder.example.myklan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class SharedSpace extends AppCompatActivity {
    String authenticator="";
    String userid="";


    private final String tag = "Accounts BUTTON CALL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_space);

        SharedPreferences mPrefs = getSharedPreferences("authentication",0);
        authenticator = mPrefs.getString("authenticator", "");
        userid = mPrefs.getString("userId", "");
    }

    public void sharedAccounts(View v)
    {
        GetAccounts(userid);
        Intent SharedAccountsPage=new Intent(getApplicationContext(), SharedAccounts.class);
        startActivity(SharedAccountsPage);
    }

    public void sharedContacts(View v)
    {
        Intent SharedContactsPage=new Intent(getApplicationContext(), SharedContacts.class);
        startActivity(SharedContactsPage);
    }





    public void GetAccounts(final String uid)
    {
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                HttpsURLConnection connection = null;
                try {
                    String url = "https://w4dtt62bhd.execute-api.us-east-1.amazonaws.com/dev/sharedSpace/getAccounts?userId="+userid;

                    Log.e("url",url);


                    URL loginEndPoint = new URL(url);
                    connection = (HttpsURLConnection) loginEndPoint.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Authorization",authenticator);

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
                    String accounts=response.toString();
                    SharedPreferences account = getSharedPreferences("accounts", 0);

                    SharedPreferences.Editor editor = account.edit();
                    editor.putString("accounts", accounts);
                    editor.commit();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
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
