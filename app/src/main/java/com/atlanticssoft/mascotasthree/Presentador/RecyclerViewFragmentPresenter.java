package com.atlanticssoft.mascotasthree.Presentador;

import android.content.Context;

import com.atlanticssoft.mascotasthree.Bd.ConstructorMascotas;
import com.atlanticssoft.mascotasthree.Models.Mascota;
import com.atlanticssoft.mascotasthree.VistaFragments.IRecyclerViewFragment;

import java.util.ArrayList;

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragment iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragment iRecyclerViewFragmentView, Context context)
    {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;

        // Llamo al método que me obtiene los datos
        obtenerMascotasBaseDatos();

    }


    @Override
    public void obtenerMascotasBaseDatos() {
        // Inicializo el constructor de mascotas
        constructorMascotas = new ConstructorMascotas(context);

        // Envío al contructor a que busque datos en una fuente de datos (Bd o Api) y me que los almacene en un Arraylist
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }


    @Override
    public void mostrarMascotasRV() {
        // Recupero todos los datos que tengo almacenados en el arraylist y lo muestro en el recyclerview
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));

        // Indico en que manera u orientación quiero mostrar el recyclerview
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }
}
