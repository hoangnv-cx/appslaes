package example.hoanghh99.appbnhng.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import example.hoanghh99.appbnhng.R;

public class MainActivity extends AppCompatActivity {
    Button button,button1,button2,button3;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.btnLg);
        button1=(Button)findViewById(R.id.btnSg);
        button2=(Button)findViewById(R.id.btnHomeSignUp);
        button3=(Button)findViewById(R.id.btnHome);
        textView=(TextView)findViewById(R.id.txttest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),HomeSignUp.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
            }
        });

        Map<String,String> map=new HashMap<>();
        map.put("userName","user");
        map.put("password","12345");
        Gson gson=new Gson();
        String MapData=gson.toJson(map);
        textView.setText(MapData);
        Toast.makeText(getApplicationContext(),map.toString(),Toast.LENGTH_SHORT).show();
       // textView.setText(map.toString());

    }
}
