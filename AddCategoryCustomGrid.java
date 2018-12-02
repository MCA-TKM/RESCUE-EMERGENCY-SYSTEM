package com.example.julie.rescues;

import java.util.Vector;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
public class AddCategoryCustomGrid extends BaseAdapter{
    private Context mContext;
    private final String[] web;
    private final String[] messages;

      public AddCategoryCustomGrid(Context c,String[] web,String[] mes ) {
          mContext = c;

       
          this.web = web;
         messages=mes;
      }
    @Override
    public int getCount() {
      // TODO Auto-generated method stub
      return web.length;
    }
    @Override
    public Object getItem(int position) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override
    public long getItemId(int position) {
      // TODO Auto-generated method stub
      return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      // TODO Auto-generated method stub
      View grid;
      LayoutInflater inflater = (LayoutInflater) mContext
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//          if (convertView == null) {
            grid = new View(mContext);
        grid = inflater.inflate(R.layout.addcategory_single, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
          
            textView.setText(web[position]);
         
            TextView textView1 = (TextView) grid.findViewById(R.id.grid_text1);
            
            textView1.setText(messages[position]);
//          } else {
//            grid = (View) convertView;
//          }
      return grid;
    }
}