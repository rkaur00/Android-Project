package ravinder.example.myklan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SharedSpace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_space);
    }

    public void sharedAccounts(View v)
    {
        Intent SharedAccountsPage=new Intent(getApplicationContext(), SharedAccounts.class);
        startActivity(SharedAccountsPage);
    }

    public void sharedContacts(View v)
    {
        Intent SharedContactsPage=new Intent(getApplicationContext(), SharedContacts.class);
        startActivity(SharedContactsPage);
    }
}
