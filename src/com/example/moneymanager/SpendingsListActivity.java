package com.example.moneymanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;

public class SpendingsListActivity extends ActionBarActivity {

	private SpendingsDAO datasource;
	private ArrayAdapter<Spending> adapter;
	private  ListView Spendinglist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_spendings_list);

		datasource = new SpendingsDAO(this);
	    datasource.open();

	    List<Spending> temp = datasource.getAllSpendings();
	    List<Spending> values=new ArrayList<Spending>();
	    Intent intent = getIntent();
	    String category=intent.getStringExtra("CATEGORY_CHOSEN");
	    
	    for (int i=0;i<temp.size();i++){
	    	if (temp.get(i).getCategory().equals(category)){
	    		values.add(temp.get(i));
	    	}
	    }
	    //Collections.reverse(values);
	    adapter = new ArrayAdapter<Spending>(this,android.R.layout.simple_list_item_1, values);
	    Spendinglist=(ListView)findViewById(R.id.list);
	    Spendinglist.setAdapter(adapter);

	}
		
	public void onClick(View view) {
	    @SuppressWarnings("unchecked")
	    Spending spending = null;
	    switch (view.getId()) {

	    case R.id.delete:
	      if (adapter.getCount() > 0) {
	        spending = (Spending) adapter.getItem(adapter.getCount()-1);
	        datasource.deleteSpending(spending);
	        adapter.remove(spending);
	      }
	      break;
	    }
	    adapter.notifyDataSetChanged();
	  }
	

	 @Override
	  protected void onResume() {
	    datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }


}
