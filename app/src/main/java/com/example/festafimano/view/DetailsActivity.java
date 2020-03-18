package com.example.festafimano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.festafimano.R;
import com.example.festafimano.constant.FimAnoConstants;
import com.example.festafimano.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkBoxPartipate = findViewById(R.id.checkbox_participate);
        this.mViewHolder.checkBoxPartipate.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.checkbox_participate) {

            if (this.mViewHolder.checkBoxPartipate.isChecked()) {
                //salvar a presença
                this.mSecurityPreferences.storeString(FimAnoConstants.PRESENCE_KEY, FimAnoConstants.CONFIRMATION_YES);
            } else {
                //salvar a ausencia
                this.mSecurityPreferences.storeString(FimAnoConstants.PRESENCE_KEY, FimAnoConstants.CONFIRMATION_NO);
            }
        }
    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String presence = extras.getString(FimAnoConstants.PRESENCE_KEY);
            if (presence != null && presence.equals(FimAnoConstants.CONFIRMATION_YES)) {
                this.mViewHolder.checkBoxPartipate.setChecked(false);
            } else {
                this.mViewHolder.checkBoxPartipate.setChecked(false);
            }
        }
    }

    private static class ViewHolder {
        CheckBox checkBoxPartipate;
    }
}
