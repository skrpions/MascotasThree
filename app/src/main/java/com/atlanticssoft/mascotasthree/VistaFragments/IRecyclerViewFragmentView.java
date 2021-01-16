package com.atlanticssoft.mascotasthree.VistaFragments;

import com.atlanticssoft.mascotasthree.Adapters.MascotaAdaptador;
import com.atlanticssoft.mascotasthree.Models.Mascota;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView
{
    // En esta Interfaz irán todos los métodos del RecyclerViewFragment que no interactuán directamente con la interfaz de usuario,
    // es decir, aquellos métodos que son independientes del fragment_recyclerview.xml.
    // Esto con el fin de implementar el patrón de diseño (MVP)

    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador (ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV (MascotaAdaptador adaptador);

}
