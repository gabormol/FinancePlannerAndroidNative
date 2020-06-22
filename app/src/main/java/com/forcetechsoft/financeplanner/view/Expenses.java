package com.forcetechsoft.financeplanner.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.forcetechsoft.financeplanner.MVP;
import com.forcetechsoft.financeplanner.common.GenericActivity;
import com.forcetechsoft.financeplanner.presenter.FinancePlannerPresenter;

public class Expenses extends GenericActivity<MVP.RequiredViewOps,
        MVP.ProvidedPresenterOps,
        FinancePlannerPresenter> // The conctere presenter type is important here we tell which presenter to use
        implements MVP.RequiredViewOps{

    NumberPicker numberPickerLocationPrice;
    NumberPicker numberPickerContactsPrice;
    NumberPicker numberPickerSocialContactsPrice;
    RadioButton radioButtonLocFree;
    RadioButton radioButtonLocAsk;
    RadioButton radioButtonLocPrice;
    RadioButton radioButtonContFree;
    RadioButton radioButtonContAsk;
    RadioButton radioButtonContPrice;
    RadioButton radioButtonSocContLocFree;
    RadioButton radioButtonSocContAsk;
    RadioButton radioButtonSocContPrice;
    RadioGroup radioGroupLocation;
    RadioGroup radioGroupContacts;
    RadioGroup radioGroupSocContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_options);


        // Set Radio Buttons
        initializeRadioButtons();

        initializeNumberPickers();

        initializeRadioGroups();

        super.onCreate(FinancePlannerPresenter.class,
                this);
        getPresenter().openExpenses();


    }

    private void initializeRadioButtons(){
        radioButtonLocFree = (RadioButton)findViewById(R.id.radioButtonFreeLocation);
        radioButtonLocAsk = (RadioButton)findViewById(R.id.radioButtonAskLocation);
        radioButtonLocPrice = (RadioButton)findViewById(R.id.radioButtonShareLocationFor);
        radioButtonContFree = (RadioButton)findViewById(R.id.radioButtonFreeContact);
        radioButtonContAsk = (RadioButton)findViewById(R.id.radioButtonAskContact);
        radioButtonContPrice = (RadioButton)findViewById(R.id.radioButtonShareContactFor);
        radioButtonSocContLocFree = (RadioButton)findViewById(R.id.radioButtonFreeSocial);
        radioButtonSocContAsk = (RadioButton)findViewById(R.id.radioButtonAskSocial);
        radioButtonSocContPrice = (RadioButton)findViewById(R.id.radioButtonShareSocialFor);

        // set listeners for the Radio Button group


        // initialize or read from database
        radioButtonLocAsk.setChecked(true);
        radioButtonContAsk.setChecked(true);
        radioButtonSocContAsk.setChecked(true);
    }

    public void initializeNumberPickers(){
        numberPickerLocationPrice =
                (NumberPicker) findViewById(R.id.locationSharePrice);
        numberPickerLocationPrice.setMinValue(0);
        numberPickerLocationPrice.setMaxValue(99);
        if (!radioButtonLocPrice.isChecked())
            numberPickerLocationPrice.setEnabled(false);

        numberPickerContactsPrice =
                (NumberPicker) findViewById(R.id.contactSharePrice);
        numberPickerContactsPrice.setMinValue(0);
        numberPickerContactsPrice.setMaxValue(99);
        if (!radioButtonContPrice.isChecked())
            numberPickerContactsPrice.setEnabled(false);

        numberPickerSocialContactsPrice =
                (NumberPicker) findViewById(R.id.contactShareSocialPrice);
        numberPickerSocialContactsPrice.setMinValue(0);
        numberPickerSocialContactsPrice.setMaxValue(99);
        if (!radioButtonSocContPrice.isChecked())
            numberPickerSocialContactsPrice.setEnabled(false);
    }

    public void initializeRadioGroups(){
        radioGroupLocation = (RadioGroup) findViewById(R.id.radioGroupLocation);
        radioGroupContacts = (RadioGroup) findViewById(R.id.radioGroupContacts);
        radioGroupSocContacts = (RadioGroup) findViewById(R.id.radioGroupSocialContacts);

        radioGroupLocation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                if (group.getCheckedRadioButtonId() == R.id.radioButtonShareLocationFor) {
                    numberPickerLocationPrice.setEnabled(true);
                } else {
                    numberPickerLocationPrice.setEnabled(false);
                }
            }
        });

        radioGroupContacts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                if (group.getCheckedRadioButtonId() == R.id.radioButtonShareContactFor) {
                    numberPickerContactsPrice.setEnabled(true);
                } else {
                    numberPickerContactsPrice.setEnabled(false);
                }
            }
        });

        radioGroupSocContacts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                if (group.getCheckedRadioButtonId() == R.id.radioButtonShareSocialFor) {
                    numberPickerSocialContactsPrice.setEnabled(true);
                } else {
                    numberPickerSocialContactsPrice.setEnabled(false);
                }
            }
        });
    }

    public void dontApplyAnySetting(View view) {
        goBackToMainActivity();

    }

    public void applyNewSettings(View view) {
        Log.d(TAG, "LOFASZ - creating database...");
        Log.d(TAG, "LOFASZ - presenter type: " + getPresenter().getClass().getName());
        super.getPresenter().openExpenses(); // FOR TEST
        //super.getPresenter().getDatabaseDump(); // FOR TEST
        //goBackToMainActivity();
    }

    @Override
    public void onBackPressed() {
        goBackToMainActivity();

    }

    private void goBackToMainActivity(){
        /*new Thread(new Runnable(){

            @Override
            public void run() {
                Log.d("LOFASZ", "Thread name: " + Thread.currentThread().getName());
                Intent intent = new Intent(ShareOptions.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(
                        R.animator.activity_flip_left_in, R.animator.activity_flip_left_out);
            }
        }).start();*/
        finish();
        overridePendingTransition(
                R.animator.activity_flip_left_out, R.animator.activity_flip_left_in);
    }
}
