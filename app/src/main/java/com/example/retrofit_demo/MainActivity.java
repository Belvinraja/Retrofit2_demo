package com.example.retrofit_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv);

        Retrofit r=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        json_api ja=r.create(json_api.class);
        Call<List<Post>> call=ja.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful())
                {
                    tv.setText("Code: "+response.code());
                    return;
                }
                List<Post> posts=response.body();

                for(Post post:posts)
                {
                    String Content="";
                    Content+="Id: "+post.getId()+"\n";
                    Content+="UserId: "+post.getUserId()+"\n";
                    Content+="Title: "+post.getTitle()+"\n";
                    Content+="Text: "+post.getText()+"\n\n";

                    tv.append(Content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tv.setText(t.getMessage());
            }
        });
    }
}
