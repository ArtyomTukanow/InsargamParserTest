package test_parser.insargamparsertest.Pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import test_parser.insargamparsertest.R;

public class UsersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
    }

    public void onClick(View view) {
        Intent intent = new Intent(UsersListActivity.this, UserActivity.class);
        startActivity(intent);
    }
}
