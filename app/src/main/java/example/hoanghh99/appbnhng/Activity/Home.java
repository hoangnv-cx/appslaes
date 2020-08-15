package example.hoanghh99.appbnhng.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import example.hoanghh99.appbnhng.Adapter.HomeAdapter;
import example.hoanghh99.appbnhng.Adapter.NavigationAdapter;
import example.hoanghh99.appbnhng.DTO.ClassifyDTO;
import example.hoanghh99.appbnhng.DTO.ItemDTO;
import example.hoanghh99.appbnhng.R;
import example.hoanghh99.appbnhng.Utils.RetrofitClientInstance;
import example.hoanghh99.appbnhng.service.ItemService;
import retrofit2.Call;
import retrofit2.Callback;

public class Home extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ItemDTO itemDTO;
    GridView gridView;
    ListView listView;
    String url="http://192.168.1.173:8085/web/item/list";
    List<ItemDTO> itemDTOS;
    HomeAdapter homeAdapter;
    List<ClassifyDTO> classifyDTOS;
    NavigationAdapter navigationAdapter;
    String urlClassify="http://192.168.1.173:8085/web/classify/list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        anhxa();
        ActionBar();
        getData(url);
        getDataNavigation(urlClassify);
      //  getDataa();
        OnClick();

    }

    private void OnClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassifyDTO classifyDTO=classifyDTOS.get(position);
                Toast.makeText(getApplicationContext(),classifyDTO.getId()+"",Toast.LENGTH_SHORT).show();
             //   Toast.makeText(getApplicationContext(),id+"",Toast.LENGTH_SHORT).show();

               // Toast.makeText(getApplicationContext(),view.getId()+"",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Item.class);

            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemDTO itemDTO=itemDTOS.get(position);
                Toast.makeText(getApplicationContext(),itemDTO.getId()+"",Toast.LENGTH_SHORT).show();
              //  Toast.makeText(getApplicationContext(),id+"",Toast.LENGTH_SHORT).show();

               // Toast.makeText(getApplicationContext(),id+"",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Item.class);
            }
        });
    }

//    private void getDataa() {
//        ItemService itemService = RetrofitClientInstance
//                .getRetrofitInstance()
//                .create(ItemService.class);
//        Call<List<ItemDTO>> listcall = itemService.getItemList();
//        listcall.enqueue(new Callback<List<ItemDTO>>() {
//            @Override
//            public void onResponse(Call<List<ItemDTO>> call, retrofit2.Response<List<ItemDTO>> response) {
//                ShowData(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<ItemDTO>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }
//
//    private void ShowData(List<ItemDTO> body) {
//        for (ItemDTO itemDTO:body){
//            itemDTOS.add(new ItemDTO(itemDTO.getId(),itemDTO.getName(),itemDTO.getPrice(),itemDTO.getThumbnail()));
//        }
//    }

    private void getDataNavigation(String urlClassify) {
        RequestQueue queue=Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, urlClassify, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    JSONArray jsonArray=jsonObject.getJSONArray("resaults");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        ClassifyDTO classifyDTO=new ClassifyDTO();
                        classifyDTO.setId(jsonObject1.getInt("id"));
                        classifyDTO.setName(jsonObject1.getString("name"));
                        classifyDTO.setCode(jsonObject1.getString("code"));
                        classifyDTO.setImage(jsonObject1.getString("image"));
                        classifyDTOS.add(new ClassifyDTO(classifyDTO.getId(),classifyDTO.getName(),classifyDTO.getCode(),classifyDTO.getImage()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                navigationAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);
       // classifyDTOS.add(new ClassifyDTO(1,"aaaa","code",""));
      //  classifyDTOS.add(new ClassifyDTO(1,"aaaa","code",""));
      //  classifyDTOS.add(new ClassifyDTO(1,"aaaa","code",""));
      //  classifyDTOS.add(new ClassifyDTO(1,"aaaa","code",""));
      //  classifyDTOS.add(new ClassifyDTO(1,"aaaa","code",""));
    }


    private void getData(String url) {
        RequestQueue queue=Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    JSONArray jsonArray=jsonObject.getJSONArray("resaults");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        itemDTO=new ItemDTO();
                        itemDTO.setId(jsonObject1.getInt("id"));
                        itemDTO.setName(jsonObject1.getString("name"));
                        itemDTO.setPrice(jsonObject1.getLong("price"));
                        itemDTO.setThumbnail(jsonObject1.getString("thumbnail"));
                        itemDTOS.add(new ItemDTO(itemDTO.getId(),itemDTO.getName(),itemDTO.getPrice(),itemDTO.getThumbnail()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                homeAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }

    private void anhxa() {
        listView=(ListView)findViewById(R.id.listViewHome);
        toolbar=(Toolbar)findViewById(R.id.toolbarHome);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayoutHome);
        gridView=(GridView)findViewById(R.id.gridViewHome);
        itemDTOS=new ArrayList<>();
        homeAdapter=new HomeAdapter(getApplicationContext(),itemDTOS);
        gridView.setAdapter(homeAdapter);
        classifyDTOS=new ArrayList<>();
        navigationAdapter=new NavigationAdapter(classifyDTOS,getApplicationContext());
        listView.setAdapter(navigationAdapter);

    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
}
