package com.example.hugonote.a6_projetosms;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devfigas.permission.MarshmallowActivity;
import com.devfigas.permission.MarshmallowPermission;

public class MainActivity extends MarshmallowActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText txNumero = (EditText) findViewById(R.id.txNumero);
        final EditText txMensagem = (EditText) findViewById(R.id.txMensagem);
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setCallbackPermission(new MarshmallowPermission(MainActivity.this, Manifest.permission.SEND_SMS, 1, false, "É necessário conceder a permissão para envio de SMS!") {
                    @Override
                    public void granted(String permission) {
                        String numero = txNumero.getText().toString();
                        String mensagem = txMensagem.getText().toString();

                        SmsManager msgSMS = SmsManager.getDefault();
                        msgSMS.sendTextMessage(numero,null, mensagem, null, null);
                    }
                    public void denied(String permission){
                        Toast.makeText(MainActivity.this, "Acesso negado!", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}
