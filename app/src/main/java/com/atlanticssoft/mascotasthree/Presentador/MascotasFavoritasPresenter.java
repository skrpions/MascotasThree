package com.atlanticssoft.mascotasthree.Presentador;

import android.content.Context;

import com.atlanticssoft.mascotasthree.Bd.ConstructorMascotas;
import com.atlanticssoft.mascotasthree.Models.Mascota;
import com.atlanticssoft.mascotasthree.VistaFragments.IMascotasFavoritasFragment;
import com.atlanticssoft.mascotasthree.VistaFragments.MascotasFavoritasFragment;

import java.util.ArrayList;
import java.util.zip.CheckedInputStream;

public class MascotasFavoritasPresenter implements IMascotasFavoritasPresenter {

    private IMascotasFavoritasFragment iMascotasFavoritasFragment;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> listaMascotasFavoritas;


    public MascotasFavoritasPresenter(IMascotasFavoritasFragment iMascotasFavoritasFragment, Context context)
    {
        this.iMascotasFavoritasFragment = iMascotasFavoritasFragment;
        this.context = context;

        // Llamo al método que me obtiene los datos
        obtenerMascotasFavoritasBaseDatos();
    }

    @Override
    public void obtenerMascotasFavoritasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        listaMascotasFavoritas = constructorMascotas.obtenerDatosMascotasFavoritas();
        mostrarMascotasFavoritasRV();
    }

    @Override
    public void mostrarMascotasFavoritasRV() {
        iMascotasFavoritasFragment.inicializarAdaptadorRV(iMascotasFavoritasFragment.crearAdaptador(listaMascotasFavoritas));
        iMascotasFavoritasFragment.generarLinearLayoutVertical();
    }
}
