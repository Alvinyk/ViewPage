package com.example.alvin.viewpage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class FragmentHome extends Fragment{

	private View  view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		if(view == null){
			view = inflater.inflate(R.layout.fragment_home, container, false);
		}

		ViewGroup parent = (ViewGroup) view.getParent();
		if(parent != null){
			parent.removeView(view);
		}

		RadioGroup rg = (RadioGroup) view.findViewById(R.id.rg_test);
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup arg0, int rdId) {
					// TODO Auto-generated method stub
				switch (rdId) {
					case R.id.btn_1:
						showEdAlertDialog();
						//Toast.makeText(getActivity().getApplicationContext(), "点击有效果" ,Toast.LENGTH_SHORT).show();
						break;

					case R.id.btn_2:
						//getActivity().finish();
						break;

					default:
						break;
				}
			}
		});

		return view;
	}

	public void showEdAlertDialog()
	{
		final EditText et = new EditText(getActivity());

		new AlertDialog.Builder(getActivity()).setTitle("请输入确认信息")
				.setView(et)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getActivity().getApplicationContext(), "输入内容为："+et.getText().toString() ,Toast.LENGTH_SHORT).show();
					}
				})
				.setNegativeButton("取消",null)
				.show();
	}
}
