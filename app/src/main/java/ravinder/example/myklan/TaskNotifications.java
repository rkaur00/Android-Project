package ravinder.example.myklan;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class TaskNotifications extends AppCompatActivity {
    String authenticator = "";
    String userid = "";


    RecyclerView recylerview;
    NotificationsAdapterClass adapter;
    List<Notifications> itemsList;
    private final String tag = "Notifications BUTTON ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_notifications);
        SharedPreferences mPrefs = getSharedPreferences("authentication", 0);
        authenticator = mPrefs.getString("authenticator", "");
        userid = mPrefs.getString("userId", "");

        GetTasks(userid);
        SharedPreferences task = getSharedPreferences("tasks",0);
        String tasks = task.getString("tasks", "");
        Log.e(tag,"Taskname: " + tasks);


        itemsList=new ArrayList<>();
        recylerview=(RecyclerView) findViewById(R.id.recycler_view);
        recylerview.setHasFixedSize(true);
        recylerview.setLayoutManager(new LinearLayoutManager(this));
        try {
            JSONArray jsonarray = new JSONArray(tasks);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String name = jsonobject.getString("title");
                Log.e("name",name);
                itemsList.add(new Notifications(name));

            }

        } catch (
                JSONException e) {
            e.printStackTrace();
        }


        adapter=new NotificationsAdapterClass(this,itemsList);
        recylerview.setAdapter(adapter);


    }


    public void GetTasks(final String uid) {
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                HttpsURLConnection connection = null;
                try {
                    String url = "https://w4dtt62bhd.execute-api.us-east-1.amazonaws.com/dev/taskNotifications/getTasks?userId=" + userid;

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
                    Log.e(tag, "the response: " + response);
                    String tasks = response.toString();
                    SharedPreferences task = getSharedPreferences("tasks", 0);

                    SharedPreferences.Editor editor = task.edit();
                    editor.putString("tasks", tasks);
                    editor.commit();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        });

    }
}
