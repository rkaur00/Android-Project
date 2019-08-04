package ravinder.example.myklan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    }

    public void SignUp(View view) {

        Intent membersEmpty=new Intent(getApplicationContext(), MembersEmpty.class);
        startActivity(membersEmpty);

    }
}
