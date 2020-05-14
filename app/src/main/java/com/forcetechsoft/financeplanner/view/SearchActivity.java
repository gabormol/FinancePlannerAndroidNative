package com.forcetechsoft.financeplanner.view;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.forcetechsoft.financeplanner.common.GenericActivity;

public class SearchActivity extends GenericActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void searchOptions(View view) {
        Intent intent = new Intent(this, SearchOptions.class);
        startActivity(intent);

        overridePendingTransition(
                R.animator.activity_flip_right_in, R.animator.activity_flip_right_out);
    }

    public void searchLocation(View view) {
        Toast.makeText(SearchActivity.this, "NOT IMPLEMENTED YET!!!", Toast.LENGTH_SHORT).show();
    }

    public void searchContact(View view) {
        Toast.makeText(SearchActivity.this, "NOT IMPLEMENTED YET!!!", Toast.LENGTH_SHORT).show();
    }

    public void searchSocialContact(View view) {
        Toast.makeText(SearchActivity.this, "NOT IMPLEMENTED YET!!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        goBackToPreviousActivity();

    }

    private void goBackToPreviousActivity(){
        finish();
        overridePendingTransition(
                R.animator.activity_flip_left_out, R.animator.activity_flip_left_in);
    }
}
