/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.validadordecorrelativas;

import java.util.List;
import java.util.Map;
/**
 *
 * @author magalib
 */
public class Alumno {
    private String nombre;
    private int id;
    private int legajo;
    private List<Materia> materiasInscripcion;
    private Map<String, Boolean> materiasAprobadas;
    
    public Alumno(
            int id,
            int legajo,
            String nombre,
            List<Materia> materiasInscripcion,
            Map<String, Boolean> materiasAprobadas) {
        this.id = id;
        this.nombre = nombre;
        this.legajo = legajo;
        this.materiasInscripcion = materiasInscripcion;
        this.materiasAprobadas = materiasAprobadas;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }
    
    public int getLegajo() {
        return this.legajo;
    }
    
    public void setMateriasInscripcion(List<Materia> materias) {
        this.materiasInscripcion = materias;
    }
    
    public List<Materia>getMateriasInscripcion() {
        return materiasInscripcion;
    }
    
    public void setMateriasAprobadas(Map<String, Boolean> materias) {
        this.materiasAprobadas = materias;
    }
    
    public Map<String, Boolean> getMateriasAprobadas() {
        return this.materiasAprobadas;
    }
    
    private String getNombreMateriasInscripcion() {
        String materiasText = "Materias Inscripcion: ";
        for (Materia materia : this.materiasInscripcion){
            materiasText += materia.getNombre();
        }
        return materiasText;
    }
    
    private String getNombreMateriasAprobadas() {
        return "Materias Aprobadas: " + this.materiasAprobadas;
    }
    
    @Override
    public String toString() {
        
        return "\n" + "Alumno: " + "\n" +
                "ID: " + this.id + " " +
                "Nombre: " + this.nombre + " " +
                "Legajo: " + this.legajo + "\n" +
                getNombreMateriasInscripcion() + "\n" +
                getNombreMateriasAprobadas();
    }
    
    
}
