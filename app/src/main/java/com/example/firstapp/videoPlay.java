package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class videoPlay extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdaptor obj_adaptor;
    public static int REQUEST_PERMISSION = 1;
    File directory;
    boolean boolean_perission;
    public static ArrayList<File> fileArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        recyclerView = (RecyclerView)findViewById(R.id.listView_video);
        //phone memory and memorycard
        directory = new File("/mnt/");
        GridLayoutManager manager = new GridLayoutManager(videoPlay.this,2);
        recyclerView.setLayoutManager(manager);

        permissionForVideo();

    }

    private void permissionForVideo() {
        if((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE )!= PackageManager.PERMISSION_GRANTED)){
            if ((ActivityCompat.shouldShowRequestPermissionRationale(videoPlay.this,Manifest.permission.READ_EXTERNAL_STORAGE))){
            }
            else{
                ActivityCompat.requestPermissions(videoPlay.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_PERMISSION);
            }

        }
        else{
            boolean_perission = true;
            getFile(directory);
            obj_adaptor = new MyAdaptor(getApplicationContext(),fileArrayList);
            recyclerView.setAdapter(obj_adaptor);
        }




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                boolean_perission = true;
                getFile(directory);
                obj_adaptor = new MyAdaptor(getApplicationContext(),fileArrayList);
                recyclerView.setAdapter(obj_adaptor);

            }
            else {
                Toast.makeText(this, "Please Allow The Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

   public ArrayList<File> getFile(File directory){
        File listfile[] = directory.listFiles();
        if (listfile!=null && listfile.length>0){
            for (int i = 0;i<listfile.length;i++){
                if (listfile[i].isDirectory()){
                    getFile(listfile[i]);
                }
                else{
                    boolean_perission = false;
                    if (listfile[i].getName().endsWith(".mp4")){
                        for (int j=0;j<fileArrayList.size();j++){
                            if (fileArrayList.get(j).getName().equals(listfile[i].getName())){
                                boolean_perission = false;
                            }
                            else{

                            }
                        }
                        if (boolean_perission){
                            boolean_perission = false;
                        }
                        else{
                            fileArrayList.add(listfile[i]);
                        }
                    }
                }

            }
        }
        return  fileArrayList;
   }

}
