package com.example.julie.rescues;

import java.util.Vector;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
public class AddCategory extends Activity {
  GridView grid;
 public static  String[] g;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.addcategory);

    grid=(GridView)findViewById(R.id.grid);
  DisplayCategory();
  
  }
  void DisplayCategory()
  {
	  Vector<String> ph=new Vector<String>();
	  Vector<String> me=new Vector<String>();
	for(int i=0;i<g.length;i++)
    {
        String[] p=g[i].split(">");
        ph.add(p[0]);
        me.add(p[1]);
    }
	 String[] p1=new String[ph.size()];
	 String[] p2=new String[ph.size()];
	 for(int i=0;i<p1.length;i++)
	 {
		 p1[i]=ph.elementAt(i);
		 p2[i]=me.elementAt(i);
	 }
	  {
	 AddCategoryCustomGrid adapter = new AddCategoryCustomGrid(AddCategory.this, p1,p2);
      grid.setAdapter(adapter);
      grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view,
                                      int position, long id) {
            	  
                 // Toast.makeText(AddCategory.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
              }
          });
	  }
  }

}