package com.vvit.kishore.assignment_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    String[] flavour = {"After Dinner Blend Coffee",
            "All-American Apple Pie Flavored Coffee",
            "Almond Joy Flavored Coffee",
            "Amaretto Flavored Coffee",
            "American Spirit Coffees",
            "Apple Cider Donut Flavored Coffee",
            "Apple Strudel Flavored Coffee",
            "Apricot Cream Flavored Coffee",
            "Atlantic Blend Coffee",
            "Baked Apple Dumplin' Flavored Coffee",
            "Banana Cream Pie Flavored Coffee",
            "Banana Nut Bread Flavored Coffee",
            "Bananas Foster Flavored Coffee",
            "Big City Blend",
            "Black Forest Cake Flavored"};

    Button min,max,order;
    TextView quant,price;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        min = (Button) findViewById(R.id.idmin);
        max = (Button) findViewById(R.id.idmax);
        quant = (TextView) findViewById(R.id.idcq);
        price = (TextView) findViewById(R.id.idprice);
        order = (Button) findViewById(R.id.idplace);

        spinner = (Spinner) findViewById(R.id.idsp);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner);

            // Set popupWindow height to 500px
            popupWindow.setHeight(500);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,flavour);
        spinner.setAdapter(arrayAdapter);

        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>0){
                    count--;
                    quant.setText(""+count);
                    price.setText(""+(count*20));
                }
            }
        });

        max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                quant.setText(""+count);
                price.setText(""+(count*20));
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Order placed",Toast.LENGTH_SHORT).show();
                quant.setText(""+0);
                price.setText(""+0);
            }
        });
    }
}
