package com.example.trabajopractico1_laboratorio3.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.trabajopractico1_laboratorio3.databinding.ActivityMainBinding;
import com.example.trabajopractico1_laboratorio3.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginViewModel.getPassMLD().observe(this, inputType -> {
            binding.etPassword.setInputType(inputType);
            binding.etPassword.setSelection(binding.etPassword.getText().length());
        });

        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            loginViewModel.login(email, password);
        });

        binding.btnRegistrar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
            startActivity(intent);
        });

        binding.cbPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            loginViewModel.mostrarContraseña(isChecked);
        });

        loginViewModel.getRegistroMLD().observe(this, success -> {
            if (success) {
                Toast.makeText(getApplicationContext(), "Usuario correctamente logueado!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                intent.putExtra("cargarDatos", true);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Datos incorrectos o usuario no registrado.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
