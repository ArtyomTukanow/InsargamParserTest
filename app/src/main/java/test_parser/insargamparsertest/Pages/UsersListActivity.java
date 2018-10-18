package test_parser.insargamparsertest.Pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import test_parser.insargamparsertest.R;

public class UsersListActivity extends AppCompatActivity {

    private EditText mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        mUserName = findViewById(R.id.userNamePlainText);
    }

    public void onClick(View view) {
        Intent intent = new Intent(UsersListActivity.this, UserActivity.class);
        String name = mUserName.getText().toString();
        if(name.length() > 0) intent.putExtra(UserActivity.USER_NAME, mUserName.getText().toString());
        startActivity(intent);
    }
}
