package com.example.trabajopractico1_laboratorio3.ui.registro;

import android.app.Application;
import android.text.InputType;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.trabajopractico1_laboratorio3.model.Usuario;
import com.example.trabajopractico1_laboratorio3.request.SharedPreferencesManager;

public class RegistroViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> userGuardadoMLD;
    private MutableLiveData<Integer> passMLD;
    private MutableLiveData<Usuario> userCargadoMLD;

    public RegistroViewModel(Application application) {
        super(application);
        userGuardadoMLD = new MutableLiveData<>();
        passMLD = new MutableLiveData<>(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        userCargadoMLD = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getUserGuardadoMLD() {
        return userGuardadoMLD;
    }

    public MutableLiveData<Integer> getPassMLD() {
        return passMLD;
    }

    public MutableLiveData<Usuario> getUserCargadoMLD() {
        return userCargadoMLD;
    }

    public void registrarUsuario(Usuario usuario) {
        SharedPreferencesManager.guardar(getApplication(), usuario);
        userGuardadoMLD.postValue(true);
    }

    public void cargarDatosUsuario() {
        Usuario usuario = SharedPreferencesManager.leer(getApplication());
        userCargadoMLD.postValue(usuario);
    }

    public void mostrarContraseña(boolean isChecked) {
        if (isChecked) {
            passMLD.setValue(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            passMLD.setValue(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }
}
