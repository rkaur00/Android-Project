package ravinder.example.myklan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FamilyMembers extends AppCompatActivity {
    RecyclerView recylerview;
    MembersAdapterClass adapter;
    List<Members> itemsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_members);
        SharedPreferences mPrefs = getSharedPreferences("IDvalue",0);
        String familyMembers = mPrefs.getString("str", "");


        itemsList=new ArrayList<>();
        recylerview=(RecyclerView) findViewById(R.id.recycler_view);
        recylerview.setHasFixedSize(true);
        recylerview.setLayoutManager(new LinearLayoutManager(this));

        //member=(TextView)findViewById(R.id.textview);
        Intent intent = getIntent();
        // String familyMembers = intent.getStringExtra("familyMembers");
        Log.e("familyMembers",familyMembers);

        try {
            JSONArray jsonarray = new JSONArray(familyMembers);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String name = jsonobject.getString("name");
                Log.e("name",name);
                itemsList.add(new Members(name));

            }

        } catch (
                JSONException e) {
            e.printStackTrace();
        }

        adapter=new MembersAdapterClass(this,itemsList);
        recylerview.setAdapter(adapter);



    }
}
