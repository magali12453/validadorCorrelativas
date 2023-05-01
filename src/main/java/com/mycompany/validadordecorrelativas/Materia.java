/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.validadordecorrelativas;

import java.util.List;

/**
 *
 * @author magalib
 */
public class Materia {
    private String nombre;
    private int id;

    public Materia(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public Materia() {
        this.id = 0;
        this.nombre = "";
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
            
    public String getNombre() {
        return this.nombre;
    }
    
    @Override
    public String toString() {
        return "\n" + "Materia: " + "\n" +
                "id: " + this.id + " " +
                "nombre: " + this.nombre;
    }
    
    


}
