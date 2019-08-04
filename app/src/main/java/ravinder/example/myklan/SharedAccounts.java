package ravinder.example.myklan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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

public class SharedAccounts extends AppCompatActivity {
String authenticator="";
String userid="";


    RecyclerView recylerview;
    AccountsAdapterClass adapter;
    List<Accounts> itemsList;
    private final String tag = "Accounts BUTTON CALL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_accounts);


        SharedPreferences mPrefs = getSharedPreferences("authentication",0);
        authenticator = mPrefs.getString("authenticator", "");
        userid = mPrefs.getString("userId", "");


        SharedPreferences account = getSharedPreferences("accounts",0);
        String accounts = account.getString("accounts", "");
        Log.e(tag,"Accountname: " + accounts);

        itemsList=new ArrayList<>();
        recylerview=(RecyclerView) findViewById(R.id.recycler_view);
        recylerview.setHasFixedSize(true);
        recylerview.setLayoutManager(new LinearLayoutManager(this));
        try {
            JSONArray jsonarray = new JSONArray(accounts);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String name = jsonobject.getString("name");
                Log.e("name",name);
                itemsList.add(new Accounts(name));

            }

        } catch (
                JSONException e) {
            e.printStackTrace();
        }


        adapter=new AccountsAdapterClass(this,itemsList);
        recylerview.setAdapter(adapter);


    }




}





