package com.umg.apirestumg;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private int id;
    private String nombre;
    private String telefono;
    private String fechacumple;
    private String departamento;

    public Student(int id, String nombre, String telefono, String fechacumple, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechacumple = fechacumple;
        this.departamento = departamento;
    }

    protected Student(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        telefono = in.readString();
        fechacumple = in.readString();
        departamento = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFechacumple() {
        return fechacumple;
    }

    public String getDepartamento() {
        return departamento;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(telefono);
        dest.writeString(fechacumple);
        dest.writeString(departamento);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
