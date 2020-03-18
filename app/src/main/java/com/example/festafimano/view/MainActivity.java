package com.example.festafimano.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.festafimano.R;
import com.example.festafimano.constant.FimAnoConstants;
import com.example.festafimano.data.SecurityPreferences;

import java.util.Calendar;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    //usando a classe de formataçao de datas como variavel
    private static SimpleDateFormat SIMPLES_DATA_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        //implementando os eventos de click
        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        //usando a data do dia de hoje
        this.mViewHolder.textToday.setText(SIMPLES_DATA_FORMAT.format(Calendar.getInstance().getTime()));
        //formatando o dia e concatenando com o getDayLeft
        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeft()), getString(R.string.dias));
        this.mViewHolder.textDaysLeft.setText(daysLeft);

    }


    @Override
    protected void onResume() {
        super.onResume();
        //verificanso a presença do usuario
        this.verifyPresence();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_confirm) {
            String presence = this.mSecurityPreferences.getStoredString(FimAnoConstants.PRESENCE_KEY);
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(FimAnoConstants.PRESENCE_KEY, presence);
            startActivity(intent);
        }
    }

    //metodo para verificaçao
    private void verifyPresence() {
        //serve para buscar se o usuario vai ou nao se tem verificação (nao confirmado, sim, não)
        String presence = this.mSecurityPreferences.getStoredString(FimAnoConstants.PRESENCE_KEY);
        if (presence.equals("")) {
            this.mViewHolder.buttonConfirm.setText(getString(R.string.nao_confirmado));
        } else if (presence.equals(FimAnoConstants.CONFIRMATION_YES)) {
            this.mViewHolder.buttonConfirm.setText(getString(R.string.sim));
        } else {
            this.mViewHolder.buttonConfirm.setText(getString(R.string.nao));
        }
    }

    //metodo para a data
    private int getDaysLeft() {
        //data de hoje
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);
        //dia maximo do ano [1-365]
        Calendar calendarLastToday = Calendar.getInstance();
        int dayMax = calendarLastToday.getActualMaximum(calendarToday.DAY_OF_YEAR);

        return dayMax - today;
    }

    private static class ViewHolder {
        TextView textToday;
        TextView textDaysLeft;
        Button buttonConfirm;
    }
}
