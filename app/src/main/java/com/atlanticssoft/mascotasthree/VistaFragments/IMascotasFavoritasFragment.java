package com.atlanticssoft.mascotasthree.VistaFragments;

import com.atlanticssoft.mascotasthree.Adapters.MascotaAdaptador;
import com.atlanticssoft.mascotasthree.Adapters.MascotasFavoritasAdaptador;
import com.atlanticssoft.mascotasthree.Models.Mascota;

import java.util.ArrayList;

public interface IMascotasFavoritasFragment
{
    // En esta Interfaz irán todos los métodos de MascotasFavoritasFragment que no interactuán directamente con la interfaz de usuario,
    // es decir, aquellos métodos que son independientes del fragment_recyclerview.xml.
    // Esto con el fin de implementar el patrón de diseño (MVP)

    public void generarLinearLayoutVertical();

    public MascotasFavoritasAdaptador crearAdaptador (ArrayList<Mascota> listaMascotasFavoritas);

    public void inicializarAdaptadorRV (MascotasFavoritasAdaptador adaptador);
}
