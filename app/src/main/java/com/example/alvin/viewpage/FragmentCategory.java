package com.example.alvin.viewpage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FragmentCategory extends Fragment {

	private View view;
	public static final int TAKE_PHOTO = 1;
	private ImageView picture;
	private Uri imageUri;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(view == null){
			view = inflater.inflate(R.layout.fragment_category, container, false);
		}

		ViewGroup parent = (ViewGroup) view.getParent();
		if(parent != null){
			parent.removeView(view);
		}

		InitView();
		return view;
	}

	private void InitView()
	{
		Button btn_takePhoto = (Button) view.findViewById(R.id.take_photo);
		picture = (ImageView)view.findViewById(R.id.picture);

		btn_takePhoto.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				File outputImage = new File(getActivity().getExternalCacheDir(), "output_image.jpg");

				try {
					if (outputImage.exists()) {
						outputImage.delete();
					}
					outputImage.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (Build.VERSION.SDK_INT >= 24) {
					imageUri = FileProvider.getUriForFile(getActivity(),
							"com.example.alvin.fragmenttabhostdemo.fileprovider", outputImage);
				} else {
					imageUri = Uri.fromFile(outputImage);
				}

				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, TAKE_PHOTO);
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode,int resultCode,Intent data)
	{
		switch (requestCode){
			case TAKE_PHOTO:
				if(resultCode == Activity.RESULT_OK){
					try{
						Bitmap bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(imageUri));
						picture.setImageBitmap(bitmap);
					}catch (FileNotFoundException e){
						e.printStackTrace();
					}
				}
				break;
			default:
				break;
		}
	}

}
