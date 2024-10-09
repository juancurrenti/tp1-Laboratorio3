package com.example.trabajopractico1_laboratorio3.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.trabajopractico1_laboratorio3.databinding.ActivityRegistroBinding;
import com.example.trabajopractico1_laboratorio3.model.Usuario;
import com.example.trabajopractico1_laboratorio3.ui.login.MainActivity;

public class RegistroActivity extends AppCompatActivity {
    private RegistroViewModel registroViewModel;
    private ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        registroViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);

        registroViewModel.getPassMLD().observe(this, inputType -> {
            binding.etPassword.setInputType(inputType);
            binding.etPassword.setSelection(binding.etPassword.getText().length());
        });

        registroViewModel.getUserCargadoMLD().observe(this, usuario -> {
            if (usuario != null) {
                binding.etEmail.setText(usuario.getMail());
                binding.etPassword.setText(usuario.getPassword());
                binding.etNombre.setText(usuario.getNombre());
                binding.etApellido.setText(usuario.getApellido());
                binding.etDni.setText(String.valueOf(usuario.getDni()));
            }
        });

        if (getIntent().getBooleanExtra("cargarDatos", false)) {
            registroViewModel.cargarDatosUsuario();
        }

        binding.btnGuardar.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            String nombre = binding.etNombre.getText().toString();
            String apellido = binding.etApellido.getText().toString();
            long dni = Long.parseLong(binding.etDni.getText().toString());

            Usuario usuario = new Usuario(email, password, nombre, apellido, dni);
            registroViewModel.registrarUsuario(usuario);

            Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
            startActivity(intent);
        });

        binding.cbPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            registroViewModel.mostrarContraseña(isChecked);
        });

        registroViewModel.getUserGuardadoMLD().observe(this, guardado -> {
            if (guardado) {
                Toast.makeText(getApplicationContext(), "Usuario correctamente guardado!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Usuario no registrado.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
