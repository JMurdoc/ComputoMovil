package itson.mx.navigationtest;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import itson.mx.activities.Configuracion;
import itson.mx.service.MyService;

public class MainActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	CustomDrawerAdapter adapter;

	List<DrawerItem> dataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		startService(new Intent(this, MyService.class));

		// Create the Drawer
		navigationDrawer(savedInstanceState);

	}

	public void navigationDrawer(Bundle savedInstanceState){
		dataList = new ArrayList<DrawerItem>();
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// Add Drawer Item to dataList
		dataList.add(new DrawerItem(true)); // adding a spinner to the list

		dataList.add(new DrawerItem("Proximas"));
		dataList.add(new DrawerItem("Alertas", R.drawable.ic_flag));
		dataList.add(new DrawerItem("Opciones"));// adding a header to the list
		dataList.add(new DrawerItem("Registro", R.drawable.ic_action_cloud));
		dataList.add(new DrawerItem("Admin. Tareas", R.drawable.ic_action_about));
		dataList.add(new DrawerItem("Configuración", R.drawable.ic_action_settings));


		adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item, dataList);

		mDrawerList.setAdapter(adapter);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {

			if (dataList.get(0).isSpinner()& dataList.get(1).getTitle() != null) {
				SelectItem(2);
			} else if (dataList.get(0).getTitle() != null) {
				SelectItem(1);
			} else {
				SelectItem(0);
			}
		}
	}


	public void SelectItem(int possition) {

		Fragment fragment = null;
		Bundle args = new Bundle();
		switch (possition) {

		case 1:
			Toast.makeText(MainActivity.this, "Pos 1", Toast.LENGTH_SHORT).show();
			break;

		case 2:
			fragment = new FragmentAlert(getApplicationContext());
			args.putString(FragmentThree.ITEM_NAME, dataList.get(possition)
					.getItemName());
			args.putInt(FragmentThree.IMAGE_RESOURCE_ID, dataList
					.get(possition).getImgResID());
			break;
		case 3:
			fragment = new FragmentOne();
			args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
					.getItemName());
			args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
					.getImgResID());
			break;
		case 4:
			fragment = new FragmentTwo();
			args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
					.getItemName());
			args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
					.getImgResID());
			break;
			
		case 5:
			fragment = new FragmentTwo();
			args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
			.getItemName());
			args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
			.getImgResID());
			break;
			
		case 6:
			Intent intent = new Intent(this, Configuracion.class);
			startActivity(intent);
			break;
		
		default:
			break;
		}

		if(fragment!= null){
			fragment.setArguments(args);
			FragmentManager frgManager = getFragmentManager();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
					.commit();
		}

		mDrawerList.setItemChecked(possition, true);
//		setTitle(dataList.get(possition).getItemName());
		mDrawerLayout.closeDrawer(mDrawerList);

	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return false;
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (dataList.get(position).getTitle() == null) {
				SelectItem(position);
			}

		}
	}

}
