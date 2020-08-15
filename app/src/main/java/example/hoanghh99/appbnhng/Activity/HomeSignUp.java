package example.hoanghh99.appbnhng.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import example.hoanghh99.appbnhng.R;

public class HomeSignUp extends AppCompatActivity {
    Button facebook,signUp;
    ImageView google;
    TextView login;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sign_up);
        anhxa();
        LoadMoreData();
    }

    private void LoadMoreData() {
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }

    private void anhxa() {
        facebook=(Button)findViewById(R.id.btnFacebooke);
        signUp=(Button)findViewById(R.id.btnNextSignUp);
        google=(ImageView)findViewById(R.id.imgGoogle);
        login=(TextView)findViewById(R.id.txtLogin);
    }
}
