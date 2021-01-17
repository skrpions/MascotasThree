package com.atlanticssoft.mascotasthree.VistaFragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atlanticssoft.mascotasthree.Adapters.MascotaAdaptador;
import com.atlanticssoft.mascotasthree.Adapters.MascotasFavoritasAdaptador;
import com.atlanticssoft.mascotasthree.Models.Mascota;
import com.atlanticssoft.mascotasthree.Permisos.BluetoothActivity;
import com.atlanticssoft.mascotasthree.Presentador.IMascotasFavoritasPresenter;
import com.atlanticssoft.mascotasthree.Presentador.IRecyclerViewFragmentPresenter;
import com.atlanticssoft.mascotasthree.Presentador.MascotasFavoritasPresenter;
import com.atlanticssoft.mascotasthree.Presentador.RecyclerViewFragmentPresenter;
import com.atlanticssoft.mascotasthree.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MascotasFavoritasFragment extends Fragment implements IMascotasFavoritasFragment {

    private RecyclerView recyclerv_mascotasfavoritas;
    private MascotasFavoritasPresenter presenter;

    public MascotasFavoritasFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Asociamos la clase Fragment java con el respectivo layout xml
        View v = inflater.inflate(R.layout.fragment_mascotas_favoritas,container,false);

        FloatingActionButton myfab = v.findViewById(R.id.fab);
        myfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BluetoothActivity.class);
                startActivity(intent);
            }
        });

        myfab.setColorFilter(Color.WHITE);

        // Enlazo el recyclerview grafico con la lógica
        recyclerv_mascotasfavoritas = (RecyclerView) v.findViewById(R.id.recyclerv_mascotasfavoritas);

        // Enlazo el Presentador
        presenter = new MascotasFavoritasPresenter (this, getContext());

        return v;
    }


    @Override
    public void generarLinearLayoutVertical()
    {
        // Ahora el codigo será en terminos de recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()); // Organizo todas las tarjetas una seguida de otra
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // Orientación en la que mostraré mi lista
        recyclerv_mascotasfavoritas.setLayoutManager(linearLayoutManager);
    }

    @Override
    public MascotasFavoritasAdaptador crearAdaptador(ArrayList<Mascota> listaMascotasFavoritas) {
        MascotasFavoritasAdaptador adaptador = new MascotasFavoritasAdaptador(listaMascotasFavoritas,getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotasFavoritasAdaptador adaptador)
    {
        recyclerv_mascotasfavoritas.setAdapter(adaptador);
    }
}
