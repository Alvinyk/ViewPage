package com.example.alvin.viewpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
	// 定义FragmentTabHost对象
	private FragmentTabHost mTabHost;

	// 定义一个布局
	private LayoutInflater layoutInflater;

	// 定义数组来存放Fragment界面
	private Class fragmentArray[] = { FragmentHome.class, FragmentCategory.class,
			FragmentDown.class,FragmentUser.class, FragmentSetting.class};

	// 定义数组来存放按钮图片
	private int mImageViewArray[] = { R.drawable.main_tab_item_home, R.drawable.main_tab_item_category,
			R.drawable.main_tab_item_down, R.drawable.main_tab_item_user ,R.drawable.main_tab_item_setting};

	// Tab选项卡的文字
	private String mTextviewArray[] = { "主页", "分类", "下载","我的", "设置"};

	private final int count = fragmentArray.length;

	private List<Fragment> list = new ArrayList<>();
	private ViewPager viewpage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		initView();
		initViewPage();
	}

	/**
	 * 初始化组件
	 */
	private void initView() {
		// 实例化布局对象
		layoutInflater = LayoutInflater.from(this);

		// 实例化TabHost对象，得到TabHost
		mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.viewpage);
		// 得到fragment的个数

		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容

			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i])
					.setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			// 设置Tab按钮的背景
			mTabHost.getTabWidget().getChildAt(i)
					.setBackgroundResource(R.drawable.main_tab_item_bg);
		}


		mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				int position = mTabHost.getCurrentTab();
				viewpage.setCurrentItem(position);
			}
		});

	}

	/**
	 * 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index) {
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);

		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);

		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(mTextviewArray[index]);

		return view;
	}

	/**
	 * 初始化viewpage
	 * @antuor alvin
	 * @time 2017/7/20  15:03
	 */
	private void initViewPage(){
		viewpage = (ViewPager)findViewById(R.id.viewpage);
		viewpage.addOnPageChangeListener(this);
		Fragment fHome = new FragmentHome();
		Fragment fCate = new FragmentCategory();
		Fragment fDown = new FragmentDown();
		Fragment fUser = new FragmentUser();
		Fragment fSet  = new FragmentSetting();

		list.add(fHome);
		list.add(fCate);
		list.add(fDown);
		list.add(fUser);
		list.add(fSet);

		viewpage.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(),list));
		mTabHost.getTabWidget().setDividerDrawable(null);
	}



	@Override
	public void onPageScrollStateChanged(int arg0){

	}

	@Override
	public void onPageScrolled(int arg0,float arg1,int arg2){

	}

	@Override
	public void onPageSelected(int arg0){
		TabWidget widget = mTabHost.getTabWidget();
		int oldFocusability = widget.getDescendantFocusability();
		widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
		mTabHost.setCurrentTab(arg0);
		widget.setDescendantFocusability(oldFocusability);
	}





	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.toolbar,menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		String message = "";
		switch (item.getItemId()){
			case R.id.backup:
				message = "Backup";
				break;
			case R.id.delete:
				message = "delete";
				break;
			case R.id.settings:
				message = "settings";
				break;
			default:
				message = "";
		}

		Toast.makeText(this,"You Clicked "+ message+" button" ,Toast.LENGTH_SHORT).show();

		return true;
	}
}
