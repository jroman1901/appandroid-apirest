package com.umg.apirestumg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.util.Log;


public class MainActivity extends AppCompatActivity {


    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        studentList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        listView.setAdapter(adapter);

        // Inicializar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.63:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        // Realizar la llamada a la API
        Call<List<Student>> call = apiService.getStudents();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Student> students = response.body();
                    for (Student student : students) {
                        studentList.add("ID: " + student.getId() + ", Nombre: " + student.getNombre() +
                                ", Teléfono: " + (student.getTelefono() != null ? student.getTelefono() : "N/A") +
                                ", Fecha de Cumpleaños: " + (student.getFechacumple() != null ? student.getFechacumple() : "N/A") +
                                ", Departamento: " + student.getDepartamento());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("API Error", "Error en la solicitud: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.e("Network Error", "Error de red: " + t.getMessage());
            }
        });


    }
}