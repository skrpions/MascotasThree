package com.atlanticssoft.mascotasthree.Permisos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.atlanticssoft.mascotasthree.R;

public class BluetoothActivity extends AppCompatActivity {


    private static final int CODIGO_SOLICITUD_PERMISO = 1;
    private static final int CODIGO_SOLICITUD_HABILITAR_BLUETOOTH = 0;
    // Variables para verificar el estado del permiso y solicitar el persmiso
    private Context context;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        // Inicializo la variable context y activity
        context = getApplicationContext();
        activity = this;



    }

    public void habilitarBluetooth(View v) // Recuerda también hablitar el permiso del Bluettoh en el Manisfest
    {
        solicitarPermiso();

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null)
        {
            Toast.makeText(getApplicationContext(), "Tu dispositivo no tiene bluetoth", Toast.LENGTH_SHORT).show();
        }
        if (!bluetoothAdapter.isEnabled())
        {
            Intent habilitarBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(habilitarBluetoothIntent, CODIGO_SOLICITUD_HABILITAR_BLUETOOTH);
        }
    }

    // Verifico el estado del permiso
    public boolean verificarEstadoPermiso()
    {
        // Pregunto si el permiso del bluetooth está dado de alta en la aplicación y almaceno el resultado
        int resultado = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH);

        // Si el permiso es otorgado
        if (resultado == PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void solicitarPermiso()
    {
        // Hago una nueva verificación rápida del permiso
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.BLUETOOTH))
        {
            Toast.makeText(getApplicationContext(), "El permiso ya fue otorgado!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // Preguntare nuevamente si desea habilitar el permiso
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.BLUETOOTH}, CODIGO_SOLICITUD_PERMISO);
        }
    }

    // Hago la gestión del permiso que otorgaré
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // requestCode trae el codigo del permiso solicitado
        switch (requestCode)
        {
            case CODIGO_SOLICITUD_PERMISO:
                if (verificarEstadoPermiso())
                {
                    Toast.makeText(getApplicationContext(), "Fantástico!, Hemos activado el Bluetooth", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        Toast.makeText(getApplicationContext(), "Opss!, No está activo el permiso de Bluetooth", Toast.LENGTH_SHORT).show();

                    }
                break;

        }
    }
}