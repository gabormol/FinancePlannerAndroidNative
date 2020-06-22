package com.forcetechsoft.financeplanner.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.forcetechsoft.financeplanner.MVP;
import com.forcetechsoft.financeplanner.common.GenericActivity;
import com.forcetechsoft.financeplanner.presenter.FinancePlannerPresenter;

public class MainActivity extends GenericActivity<MVP.RequiredViewOps,
        MVP.ProvidedPresenterOps,
        FinancePlannerPresenter> // The conctere presenter type is important here we tell which presenter to use
        implements MVP.RequiredViewOps {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(FinancePlannerPresenter.class,
                this);

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

        if (!getPresenter().getLoginStatus()){
            Toast.makeText(MainActivity.this, "NOT LOGGED IN!!!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, Expenses.class);
            startActivity(intent);

            overridePendingTransition(
                    R.animator.activity_flip_right_in, R.animator.activity_flip_right_out);
        }

    }

    public void searchBtn(View view) {
        // Get the user input (if any).

        if (!getPresenter().getLoginStatus()){
            Toast.makeText(MainActivity.this, "NOT LOGGED IN!!!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, Timesheet.class);
            startActivity(intent);

            overridePendingTransition(
                    R.animator.activity_flip_right_in, R.animator.activity_flip_right_out);
        }

    }

    public void accountBtn(View view) {
        if (!getPresenter().getLoginStatus()){
            Toast.makeText(MainActivity.this, "NOT LOGGED IN!!!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, Balance.class);
            startActivity(intent);

            overridePendingTransition(
                    R.animator.activity_flip_right_in, R.animator.activity_flip_right_out);
        }
    }

    public void settingsBtn(View view){
        Toast.makeText(MainActivity.this, "NOT IMPLEMENTED YET!!!", Toast.LENGTH_SHORT).show();
    }
}
