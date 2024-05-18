package com.umg.apirestumg;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("students")
    Call<List<Student>> getStudents();
}
