package com.forcetechsoft.financeplanner.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.forcetechsoft.financeplanner.MVP;
import com.forcetechsoft.financeplanner.common.GenericActivity;
import com.forcetechsoft.financeplanner.presenter.FinancePlannerPresenter;

public class Timesheet extends GenericActivity<MVP.RequiredViewOps,
        MVP.ProvidedPresenterOps, FinancePlannerPresenter>
        implements MVP.RequiredViewOps {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        super.onCreate(FinancePlannerPresenter.class,
                this);
        getPresenter().openTimesheet();
    }

    public void searchOptions(View view) {
        Intent intent = new Intent(this, Balance.class);
        startActivity(intent);

        overridePendingTransition(
                R.animator.activity_flip_right_in, R.animator.activity_flip_right_out);
    }

    public void searchLocation(View view) {
        Toast.makeText(Timesheet.this, "NOT IMPLEMENTED YET!!!", Toast.LENGTH_SHORT).show();
    }

    public void searchContact(View view) {
        Toast.makeText(Timesheet.this, "NOT IMPLEMENTED YET!!!", Toast.LENGTH_SHORT).show();
    }

    public void searchSocialContact(View view) {
        Toast.makeText(Timesheet.this, "NOT IMPLEMENTED YET!!!", Toast.LENGTH_SHORT).show();
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
