package com.example.moneymanager;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.method.DateTimeKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class MoneyInputActivity extends ActionBarActivity {
	private SpendingsDAO datasource;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_money_input);	
		datasource = new SpendingsDAO(this);
	    datasource.open();
	}

	public void onClickSubmit(View view) {
		EditText text=(EditText)findViewById(R.id.priceInput);
		System.out.println(text.getText().toString());
		Intent intent = getIntent();
		String category=intent.getStringExtra("CATEGORY_CHOSEN");
    	Intent myintent=new Intent(view.getContext(),SpendingsListActivity.class);
    	myintent.putExtra("CATEGORY_CHOSEN", category);
	    String price=text.getText().toString();
	    if (!price.equals("") && price!=null){
	    	double p=Double.parseDouble(price);
	    	myintent.putExtra("PRICE", p);
	    	datasource.createSpending(p,category,java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()).toString());
	    }
	    startActivity(myintent);
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
