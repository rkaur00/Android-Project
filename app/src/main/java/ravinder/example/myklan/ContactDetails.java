package ravinder.example.myklan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContactDetails extends AppCompatActivity {
    private final String tag = "Accounts BUTTON CALL";
    private TextView contactName;
    private TextView contactNumber;

    private TextView contactInfo;
    String info="Nothing to show";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Intent intent = getIntent();
        String message = intent.getStringExtra("quantity");
        //int position=Integer.parseInt(message);
        contactName=(TextView)findViewById(R.id.name);

        contactNumber=(TextView)findViewById(R.id.number);
        contactInfo=(TextView)findViewById(R.id.info);

        Log.e("Position", "" + message);

        int position = Integer.parseInt(message);
        Log.e("Position", "" + position);

        SharedPreferences contact = getSharedPreferences("Contacts",0);
        String contacts = contact.getString("contacts", "");
        Log.e("Contacts", "" + contacts);

        try {
            JSONArray jsonarray = new JSONArray(contacts);

            JSONObject jsonobject = jsonarray.getJSONObject(position);
            String name = jsonobject.getString("name");
            String password = jsonobject.getString("number");
            if(jsonobject.has("info")) {
                info = jsonobject.getString("info");
            }
            Log.e("name", name);
            contactName.setText(name);
            contactNumber.setText(password);
            contactInfo.setText(info);


        } catch (
                JSONException e) {
            e.printStackTrace();
        }

    }

}
