package com.example.logindepression;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registrarse extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText correo, password, vpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        mAuth = FirebaseAuth.getInstance();
        correo = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        vpassword = findViewById(R.id.et_verifpassword);
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void registrarUsuario(View view){
        if(password.getText().toString().equals(vpassword.getText().toString())){
            mAuth.createUserWithEmailAndPassword(correo.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Usuario registrado", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(), "Autenticación fallida", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            Toast.makeText(this, "Las contraseñas no coinciden :c", Toast.LENGTH_SHORT).show();
        }
    }
}