package com.example.prctica1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.prctica1.databinding.FragmentHomeBinding;
import com.example.prctica1.dto.Cat;
import com.example.prctica1.dto.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textHome;
        final Button button = binding.btnEnviar;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://catfact.ninja/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api api = retrofit.create(Api.class);
                Call<Cat> call = api.datos();
                 call.enqueue(new Callback<Cat>() {
                     @Override
                     public void onResponse(Call<Cat> call, Response<Cat> response) {
                         if (response.isSuccessful()){
                             Cat cat =  response.body();
                             textView.setText(cat.getFact());
                             //Toast.makeText(getContext(),cat.getLenght(), Toast.LENGTH_SHORT).show();
                         }else{
                            textView.setText("Algo salio mal");
                         }
                     }

                     @Override
                     public void onFailure(Call<Cat> call, Throwable t) {
                         textView.setText("Algo salio mal");
                     }
                 });

            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}