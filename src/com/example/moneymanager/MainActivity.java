package com.example.moneymanager;

import java.util.ArrayList; 
import java.util.List; 
import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
	//private SpendingsDAO datasource;
	private ArrayAdapter<String> adapter;
	private  ListView Categorylist;
	private List<String> Categories;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		Categories=new ArrayList<String>();
		Categories.add("Foods");
		Categories.add("Clothing");
		Categories.add("Recreation/Leisure");
		Categories.add("Home");

	    adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Categories);
	    Categorylist=(ListView)findViewById(R.id.list);
	    Categorylist.setAdapter(adapter);
	    
	    Categorylist.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
               
                String category = ((TextView)view).getText().toString();              
                System.out.println(category);
                Intent intent=new Intent(view.getContext(),MoneyInputActivity.class);
                intent.putExtra("CATEGORY_CHOSEN", category);
                startActivity(intent);
               
            }
        });
	    
	}

	 @Override
	  protected void onResume() {
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    super.onPause();
	  }


}
