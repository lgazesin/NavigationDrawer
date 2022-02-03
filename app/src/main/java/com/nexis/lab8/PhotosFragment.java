package com.nexis.lab8;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PhotosFragment extends Fragment {
    ImageView pic;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photos, container, false);
        ImageView pic = (ImageView) rootView.findViewById(R.id.image_view);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseProfilePicture(view);
            }
        });
        return rootView;
    }

    private void chooseProfilePicture(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_photos, null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        ImageView  imageViewADPPCamera = dialogView.findViewById(R.id.image_view);

       AlertDialog alertDialogProfilePicture = builder.create();
       alertDialogProfilePicture.show();

       imageViewADPPCamera.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               takePictureFromGallery();
               alertDialogProfilePicture.cancel();
           }
       });
    }

    private void takePictureFromGallery(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto,1);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (resultCode == -1){
                    Uri selectedImageUri = data.getData();
                    pic.setImageURI(selectedImageUri);
                }
                break;
        }
    }


}
