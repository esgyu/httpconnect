package com.example.httpconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_SHORT;

public class ConnectActivity extends AppCompatActivity {
    private Socket socket;
    EditText et_text;
    Button bt_send;
    private Socket mSocket;
    {
        try{
            mSocket = IO.socket("http://192.168.0.21:5000");
        } catch (URISyntaxException e) {}
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        mSocket.connect();

        et_text = (EditText) findViewById(R.id.et_text);
        bt_send = (Button)  findViewById(R.id.bt_send);

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = et_text.getText().toString().trim();
                if(TextUtils.isEmpty(message)){
                    return;
                }
                et_text.setText("");
                mSocket.emit("message", message);
                System.out.println(message + "is sent");
            }
        });
    }

    @Override
    public void onDestroy() {
        mSocket.disconnect();
        super.onDestroy();
    }
}
