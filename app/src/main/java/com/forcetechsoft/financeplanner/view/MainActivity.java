package com.forcetechsoft.financeplanner.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.forcetechsoft.financeplanner.common.GenericActivity;

public class MainActivity extends GenericActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*final Button shareButton = (Button) findViewById(R.id.button_share);
        shareButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                new Thread (new Runnable() {
                    @Override
                    public void run() {
                        Log.d("LOFASZ", "Thread: " + Thread.currentThread().getName());
                        Intent intent = new Intent(MainActivity.this, ShareOptions.class);
                        startActivity(intent);
                        overridePendingTransition(
                                R.animator.activity_flip_right_in, R.animator.activity_flip_right_out);
                    }
                }).start();

            }
        });*/
    }

    public void shareBtn(View view) {
        // Get the user input (if any).

        Intent intent = new Intent(this, ShareOptions.class);
        startActivity(intent);

        overridePendingTransition(
                R.animator.activity_flip_right_in, R.animator.activity_flip_right_out);


    }

    public void searchBtn(View view) {
        // Get the user input (if any).

        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);

        overridePendingTransition(
                R.animator.activity_flip_right_in, R.animator.activity_flip_right_out);

    }

    public void accountBtn(View view) {
        // Get the user input (if any).
        Toast.makeText(MainActivity.this, "NOT IMPLEMENTED YET!!!", Toast.LENGTH_SHORT).show();

    }

    public void settingsBtn(View view){
        Toast.makeText(MainActivity.this, "NOT IMPLEMENTED YET!!!", Toast.LENGTH_SHORT).show();
    }
}
