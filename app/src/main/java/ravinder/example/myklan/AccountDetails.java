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

public class AccountDetails extends AppCompatActivity {

    private final String tag = "Accounts BUTTON CALL";
    private TextView accountName;
    private TextView accountUsername;
    private TextView accountPassword;
    private TextView accountInfo;
String info="Nothing to show";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        Intent intent = getIntent();
        String message = intent.getStringExtra("quantity");
        //int position=Integer.parseInt(message);
        accountName=(TextView)findViewById(R.id.name);
        accountUsername=(TextView)findViewById(R.id.username);
        accountPassword=(TextView)findViewById(R.id.number);
        accountInfo=(TextView)findViewById(R.id.info);

        Log.e("Position", "" + message);

            int position = Integer.parseInt(message);
        Log.e("Position", "" + position);

            SharedPreferences account = getSharedPreferences("accounts", 0);
            String accounts = account.getString("accounts", "");
            try {
                JSONArray jsonarray = new JSONArray(accounts);

                    JSONObject jsonobject = jsonarray.getJSONObject(position);
                    String name = jsonobject.getString("name");
                    String password = jsonobject.getString("password");
                    String username = jsonobject.getString("username");
                       if(jsonobject.has("info")) {
                           info = jsonobject.getString("info");
                       }
                    Log.e("name", name);
                    accountName.setText(name);
                    accountUsername.setText(username);
                    accountPassword.setText(password);
                 accountInfo.setText(info);


            } catch (
                    JSONException e) {
                e.printStackTrace();
            }

            Log.e(tag, "Accountname: " + accounts);
        }

}
