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

import example.hoanghh99.appbnhng.DTO.UserDTO;
import example.hoanghh99.appbnhng.R;
import example.hoanghh99.appbnhng.Utils.RetrofitClientInstance;
import example.hoanghh99.appbnhng.service.loginService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUp extends AppCompatActivity {
    EditText userName,fullName,password;
    Switch accept;
    Button signUp;

    String url="http://192.168.1.173:8085/login";
    String messege;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        anhxa();
        LoadMoreData();
    }
    private void LoadMoreData() {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDTO userDTO=new UserDTO();
                userDTO.setUserName(userName.getText().toString().trim());
                userDTO.setPassword(password.getText().toString().trim());
                userDTO.setFullName(fullName.getText().toString().trim());
                if (userDTO.getUserName().equals("")){

                }else if (userDTO.getPassword().equals("")){

               // }else if(userDTO.getFullName().equals("")){

                }else {
                   // postData(url,userDTO.getUsername(),userDTO.getPassword(),userDTO.getFullName());
                    PostData(userDTO.getUserName(),userDTO.getPassword(),userDTO.getFullName());
                }
            }


        });
    }
    private void PostData(String userName,String password,String fullName) {

        loginService itemService = RetrofitClientInstance
                .getRetrofitInstance()
                .create(loginService.class);
        UserDTO userDTOs=new UserDTO(userName,password);

        retrofit2.Call<UserDTO> listcall =itemService.postDataSignup(userDTOs);
        listcall.enqueue(new retrofit2.Callback<UserDTO>() {
            @Override
            public void onResponse(retrofit2.Call<UserDTO> call, retrofit2.Response<UserDTO> response) {
                getData(response.body());
            }

            @Override
            public void onFailure(retrofit2.Call<UserDTO> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getData(UserDTO body) {
        Toast.makeText(getApplicationContext(),"oke",Toast.LENGTH_LONG).show();
    }
//    private void postData(String url, String userName, String password, String fullName) {
//        MediaType MEDIA_TYPE = MediaType.parse("application/json");
//        OkHttpClient client = new OkHttpClient();
//        JSONObject postdata = new JSONObject();
//        try {
//            postdata.put("userName", userName);
//            postdata.put("password", password);
//           // postdata.put("password", fullName);
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
//                messege=response.toString();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),messege,Toast.LENGTH_LONG).show();
//                        }
//                    });
//
//            }
//        });
//
//
//
//    }
    private void anhxa() {
        userName=(EditText)findViewById(R.id.edtUserNameSignUp);
        fullName=(EditText)findViewById(R.id.edtFullNameSignUp);
        password=(EditText)findViewById(R.id.edtPasswordSignUp);
        accept=(Switch)findViewById(R.id.switchSignUp);
        signUp=(Button)findViewById(R.id.btnSignUp);
    }
}
