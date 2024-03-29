package com.example.aaaa;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.aaaa.api.APITrappy;
import com.example.aaaa.api.Client;
import com.example.aaaa.models.Message;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NewHome extends AppCompatActivity {
    CardView playCard;
    CardView perfilCard;
    CardView tiendaCard;
    CardView pinkCard;
    CardView virtualCard;
    CardView scientistCard;
    Button logout;
    TextView mensaje;
    APITrappy apiTrappy;
    ProgressBar progressBar;
    static int REQUEST_CODE_1 = 1;
    static String[] miVector2 = new String[100];
    private void clearAuthenticationInfo() {
        SharedPreferences sharedPref = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("is_authenticated");
        editor.apply();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NewHome.REQUEST_CODE_1 && resultCode == RESULT_OK && data != null) {
            String input2 = data.getStringExtra("input2");
            //result.setText("Result from Activity is: "+input2); Aquí habría que poner algo para obtener el resultado (un TextView).
        } else {
            //result.setText("Activity result received but not correct!");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);
        progressBar = findViewById(R.id.progressBar5);
        progressBar.setVisibility(View.GONE);
        apiTrappy = Client.getInstance().getApiTrappy();

        mensaje = (TextView) findViewById(R.id.message);
        apiTrappy = com.example.aaaa.api.Client.getInstance().getApiTrappy();
        //Recogemos los datos que nos mandan desde la función getMessage() del backend (una lista de mensajes)
        apiTrappy.getMessages().enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.isSuccessful()) {
                    Message message1 = response.body();
                    mensaje.setText(message1.getCuerpoMensaje());
                } else {
                    Log.d("ERROR MENSAJE", "HA HABIDO UN ERROR RECIBIENDO LOS MENSAJES");
                }
            }
            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.d("ERROR MENSAJE", "HA HABIDO UN ERROR RECIBIENDO LOS MENSAJES");
            }
        });

        logout = findViewById(R.id.logoutbtn);

        Timer timer = new Timer();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                progressBar.setVisibility(View.VISIBLE);
                clearAuthenticationInfo();
                Intent intent = new Intent(NewHome.this, LogIn.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        playCard = findViewById(R.id.playCard);

        playCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent play = new Intent (); //En vez del segundo Home.class hay que poner
                //la actividad en la que esté el juego.
                play.setComponent(new ComponentName("com.DefaultCompany.CROACKY", "com.unity3d.player.UnityPlayerActivity"));

                play.putExtra("input","Aquí iría la info que queramos");
                startActivityForResult(play, NewHome.REQUEST_CODE_1);
            }
        });



        /*

        ESTE ES EL EJEMPLO DE GITHUB https://github.com/jlopezr/activities/blob/main/app/src/main/java/dsa/app1/Activity1.java

          public void onClick4(View view) {
        Intent i = new Intent();
        i.setComponent(new ComponentName("dsa.app3", "com.unity3d.player.UnityPlayerActivity"));

        String data = input.getText().toString();
        i.putExtra("input",data);
        //startActivity(i);
        startActivityForResult(i, Activity2.REQUEST_CODE_1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Activity2.REQUEST_CODE_1 && resultCode == RESULT_OK && data != null) {
            String input2 = data.getStringExtra("input2");
            result.setText("Result from Activity is: "+input2);
        } else {
            result.setText("Activity result received but not correct!");
        }
    }
         */

        perfilCard = findViewById(R.id.perfilCard);

        perfilCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent perfil = new Intent (NewHome.this, Perfil.class);
                startActivity(perfil);
            }
        });

        tiendaCard = findViewById(R.id.tiendaCard);

        tiendaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent (NewHome.this, ShopDashboard.class);
                startActivity(i);
            }
        });

        pinkCard = findViewById(R.id.pinkCard);

        pinkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        virtualCard = findViewById(R.id.virtualCard);

        virtualCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        scientistCard = findViewById(R.id.scientistCard);

        scientistCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}