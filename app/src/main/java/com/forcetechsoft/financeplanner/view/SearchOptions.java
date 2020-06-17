package com.forcetechsoft.financeplanner.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.forcetechsoft.financeplanner.MVP;
import com.forcetechsoft.financeplanner.common.GenericActivity;
import com.forcetechsoft.financeplanner.presenter.FinancePlannerPresenter;

public class SearchOptions extends GenericActivity<MVP.RequiredViewOps,
        MVP.ProvidedPresenterOps, FinancePlannerPresenter>
        implements MVP.RequiredViewOps {

    NumberPicker numberPickerLocationPrice;
    NumberPicker numberPickerContactsPrice;
    NumberPicker numberPickerSocialContactsPrice;
    RadioButton radioButtonLocFree;
    RadioButton radioButtonLocPrice;
    RadioButton radioButtonContFree;
    RadioButton radioButtonContPrice;
    RadioButton radioButtonSocContFree;
    RadioButton radioButtonSocContPrice;
    RadioGroup radioGroupLocation;
    RadioGroup radioGroupContacts;
    RadioGroup radioGroupSocContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_options);
        super.onCreate(FinancePlannerPresenter.class,
                this);
        getPresenter().openBalance();

        // Set Radio Buttons
        initializeRadioButtons();

        initializeNumberPickers();

        initializeRadioGroups();
    }

    private void initializeRadioButtons(){
        radioButtonLocFree = (RadioButton)findViewById(R.id.radioButtonFreeLocationSearch);
        radioButtonLocPrice = (RadioButton)findViewById(R.id.radioButtonPaidLocationSearch);
        radioButtonContFree = (RadioButton)findViewById(R.id.radioButtonFreeContactSearch);
        radioButtonContPrice = (RadioButton)findViewById(R.id.radioButtonPaidContactSearch);
        radioButtonSocContFree = (RadioButton)findViewById(R.id.radioButtonFreeSocialSearch);
        radioButtonSocContPrice = (RadioButton)findViewById(R.id.radioButtonPaidSocialContactSeatch);

        // set listeners for the Radio Button group


        // initialize or read from database
        radioButtonLocFree.setChecked(true);
        radioButtonContFree.setChecked(true);
        radioButtonSocContFree.setChecked(true);
    }

    public void initializeNumberPickers(){
        numberPickerLocationPrice =
                (NumberPicker) findViewById(R.id.locationSearchPrice);
        numberPickerLocationPrice.setMinValue(0);
        numberPickerLocationPrice.setMaxValue(99);
        if (!radioButtonLocPrice.isChecked())
            numberPickerLocationPrice.setEnabled(false);

        numberPickerContactsPrice =
                (NumberPicker) findViewById(R.id.contactSearchPrice);
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
        radioGroupLocation = (RadioGroup) findViewById(R.id.radioGroupLocationSearch);
        radioGroupContacts = (RadioGroup) findViewById(R.id.radioGroupContactsSearch);
        radioGroupSocContacts = (RadioGroup) findViewById(R.id.radioGroupSocialContactsSearch);

        radioGroupLocation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                if (group.getCheckedRadioButtonId() == R.id.radioButtonPaidLocationSearch) {
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

                if (group.getCheckedRadioButtonId() == R.id.radioButtonPaidContactSearch) {
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

                if (group.getCheckedRadioButtonId() == R.id.radioButtonPaidSocialContactSeatch) {
                    numberPickerSocialContactsPrice.setEnabled(true);
                } else {
                    numberPickerSocialContactsPrice.setEnabled(false);
                }
            }
        });
    }

    public void dontApplyAnySetting(View view) {
        goBackToPreviousActivity();

    }

    public void applyNewSettings(View view) {
        goBackToPreviousActivity();
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