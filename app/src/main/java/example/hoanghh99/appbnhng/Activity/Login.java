package example.hoanghh99.appbnhng.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import example.hoanghh99.appbnhng.DTO.ItemDTO;
import example.hoanghh99.appbnhng.DTO.UserDTO;
import example.hoanghh99.appbnhng.R;
import example.hoanghh99.appbnhng.Utils.RetrofitClientInstance;
import example.hoanghh99.appbnhng.service.loginService;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;

public class Login extends AppCompatActivity {

    EditText edtUserName,edtPassword;
    Switch remeber;
    Button btnLogin;
    String messege;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();
        LoadMoreData();


    }

    private void PostData(String userName,String password) {
        loginService itemService = RetrofitClientInstance
                .getRetrofitInstance()
                .create(loginService.class);
         UserDTO userDTOs=new UserDTO(userName,password);
         Call<UserDTO> listcall =itemService.postDataLogin(userDTOs);
         listcall.enqueue(new retrofit2.Callback<UserDTO>() {
             @Override
             public void onResponse(Call<UserDTO> call, retrofit2.Response<UserDTO> response) {
                 getData(response.body());
             }

             @Override
             public void onFailure(Call<UserDTO> call, Throwable t) {
                 Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
             }
         });
    }

    private void getData(UserDTO body) {
        Toast.makeText(getApplicationContext(),body.getToken(),Toast.LENGTH_LONG).show();
    }

//    private void getToken(String url,String user,String password) {
//        MediaType MEDIA_TYPE = MediaType.parse("application/json");
//        OkHttpClient client = new OkHttpClient();
//        JSONObject postdata = new JSONObject();
//        try {
//            postdata.put("username", user);
//            postdata.put("password", password);
//        } catch(JSONException e){
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());
//
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .header("Accept", "application/json")
//                .header("Content-Type", "application/json")
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                messege=e.getMessage().toString();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),messege,Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try {
//
//                    JSONObject jsonObject=new JSONObject(response.body().string());
//                    userDTO.setToken(jsonObject.getString("token"));
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),userDTO.getToken(),Toast.LENGTH_LONG).show();
//                        }
//                    });
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    private void LoadMoreData() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDTO userDTO=new UserDTO();
                userDTO.setUserName(edtUserName.getText().toString().trim());
                userDTO.setPassword(edtPassword.getText().toString().trim());

                    if(userDTO.getUserName().equals("")){
                        Toast.makeText(getApplicationContext(),"USer Name do not null",Toast.LENGTH_LONG).show();
                    }else if(userDTO.getPassword().equals("")){
                        Toast.makeText(getApplicationContext(),"Password do not null",Toast.LENGTH_LONG).show();
                    }else {

                       // getToken(url,userDTO.getUserName(),userDTO.getPassword());
                        PostData(userDTO.getUserName(),userDTO.getPassword());
                    }
                }

        });
    }

    private void anhxa() {
        edtUserName=(EditText)findViewById(R.id.edtUserNameLogin);
        edtPassword=(EditText)findViewById(R.id.edtPasswordLogin);
        remeber=(Switch)findViewById(R.id.switchLogin);
        btnLogin=(Button)findViewById(R.id.btnLogin);

    }
}



