package com.example.trabajopractico1_laboratorio3.ui.login;

import android.app.Application;
import android.text.InputType;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.trabajopractico1_laboratorio3.model.Usuario;
import com.example.trabajopractico1_laboratorio3.request.SharedPreferencesManager;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuarioMLD;
    private MutableLiveData<Integer> passMLD;
    private MutableLiveData<Boolean> registroMLD;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        usuarioMLD = new MutableLiveData<>();
        passMLD = new MutableLiveData<>(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        registroMLD = new MutableLiveData<>();
    }

    public MutableLiveData<Usuario> getUsuarioMLD() {
        return usuarioMLD;
    }

    public MutableLiveData<Integer> getPassMLD() {
        return passMLD;
    }

    public MutableLiveData<Boolean> getRegistroMLD() {
        return registroMLD;
    }

    public void login(String email, String password) {
        Usuario usuario = SharedPreferencesManager.login(getApplication(), email, password);
        usuarioMLD.postValue(usuario);

        if (usuario != null) {
            registroMLD.postValue(true);
        } else {
            registroMLD.postValue(false);
        }
    }

    public void mostrarContraseña(boolean isChecked) {
        if (isChecked) {
            passMLD.setValue(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            passMLD.setValue(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }
}
