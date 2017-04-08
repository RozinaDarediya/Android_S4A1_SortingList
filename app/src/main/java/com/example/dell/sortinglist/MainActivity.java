package com.example.dell.sortinglist;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    Button acndBtn;
    Button descBtn;
    ListView listView;

    List<String> mnthString;
    private StringAdapter stringAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acndBtn = (Button) findViewById(R.id.acndBtn);
        descBtn = (Button) findViewById(R.id.dsndBtn);
        listView = (ListView) findViewById(R.id.listview);

        mnthString = new ArrayList<String>();
        mnthString.add("Jan");
        mnthString.add("Feb");
        mnthString.add("March");
        mnthString.add("April");
        mnthString.add("May");
        mnthString.add("June");
        mnthString.add("July");
        mnthString.add("Aug");
        mnthString.add("Sep");
        mnthString.add("Oct");
        mnthString.add("Nov");
        mnthString.add("Dec");

        stringAdapter = new StringAdapter(MainActivity.this, R.layout.sort_list_item, mnthString);
        listView.setAdapter((ListAdapter) stringAdapter);

        acndBtn.setOnClickListener(this);
        descBtn.setOnClickListener(this);

    }

    public static Comparator<String> StringAscComparator = new Comparator<String>() {

        public int compare(String app1, String app2) {

            String stringName1 = app1;
            String stringName2 = app2;

            return stringName1.compareToIgnoreCase(stringName2);
        }
    };
    private static Comparator<String> StringDescComparator = new Comparator<String>() {
        @Override
        public int compare(String app1, String app2) {

            String stringName1 = app1;
            String stringName2 = app2;

            return stringName2.compareToIgnoreCase(stringName1);
        }
    };

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.acndBtn :
                Collections.sort(mnthString, StringAscComparator);
                Toast.makeText(MainActivity.this, "Sorting in Ascending Order", Toast.LENGTH_LONG).show();
                break;
            case R.id.dsndBtn :
                Collections.sort(mnthString, StringDescComparator);
                Toast.makeText(MainActivity.this, "Sorting in Descending Order", Toast.LENGTH_LONG).show();
                break;
        }
        stringAdapter.notifyDataSetChanged();
    }

    private class StringAdapter extends ArrayAdapter<String> {

        private List<String> strModel;

        public StringAdapter(Context context, int textViewResourceId, List<String> strModel) {
            super(context, textViewResourceId, strModel);
            this.strModel = strModel;
        }
        @Override
        public View getView(int position, View convertView , ViewGroup parent) {
            View view = convertView;
            Holder holder = null;

            if (view == null) {
                view = View.inflate(MainActivity.this, R.layout.sort_list_item, null);

                holder = new Holder();
                holder.StringNameTextView =  (TextView) view.findViewById(R.id.name_text_view);

                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            String nameText=strModel.get(position);
            holder.StringNameTextView.setText(nameText);
            return view;
        }
        }

        private class Holder {
            private TextView StringNameTextView;
        }
 }


   
