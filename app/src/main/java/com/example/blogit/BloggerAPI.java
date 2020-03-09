package com.example.blogit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class BloggerAPI {
    private static final String key = "AIzaSyB2nOQMJCL0KVDcr1mq--S4ApNxPPFz-GQ";
    private static final String url = "https://www.googleapis.com/blogger/v3/blogs/7776652313551988298/posts/";

    public static PostService postService = null;

    public static PostService getService(){
        if(postService==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create()) //check manifest to use java compileOptions in gradel
                    .build();

            postService= retrofit.create(PostService.class);
        }
        return postService;
    }

    public interface PostService{
        @GET("?key="+key)
        Call<PostList> getPostList();
        @GET("(postId)/?key="+key)
        Call<Item> getPostItem(@Path("postId") String Id);
    }
}
