/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.validadordecorrelativas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author magalib
 */
public class ListaMaterias {
    private List<Materia> materias;
    private final String dataBase = "./database.db";
    private final String leeMateriasQuery = "SELECT idMateria, nombre, correlativa1, correlativa2, correlativa3 FROM materias";
    private final String leeCorrelativasQuery = "SELECT correlativa1, correlativa2, correlativa3 FROM materias WHERE idMateria=";
    
    public ListaMaterias() {
        this.materias = cargaMaterias();
    }
    
    public Materia getMateria(int id) {
        Materia materiaExp = new Materia();
        for(Materia materia : materias) {
            if(materia.getId() == id){
                materiaExp = materia;
                break;
            }
        }
        return materiaExp;
    }
    
    public String getNombreMateria(int id) {
        String nombre = "";
        
        for(Materia materia : materias) {
            if(materia.getId() == id) {
                nombre = materia.getNombre();
                break;
            }
        }
        return nombre;
    }
    
    public List<String> getNombresCorrelativas(int id) {
        List<String> nombresCorrelativas = new ArrayList<>();
        List<Integer> idCorrelativas = cargaCorrelativasById(id);
        
        for (int idMateria : idCorrelativas) {
            nombresCorrelativas.add(getNombreMateria(idMateria));
        }
        return nombresCorrelativas;
    }
    
    private List<Materia> cargaMaterias() {
        List<Materia> materias = new ArrayList<>();
        Connection conexion = null;
        
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + this.dataBase);
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(this.leeMateriasQuery);

            while(rs.next()) {
                Materia materia = new Materia(
                    rs.getInt("idMateria"),
                    rs.getString("nombre").replace("\"", ""));
                materias.add(materia);
            }
        } catch (SQLException exepcion) {
            System.out.println(exepcion.getMessage());
            
        } finally {
            try {
                if(conexion != null) {
                    conexion.close();
                }
            } catch (Exception exepcion) {
                System.out.println(exepcion.getMessage());
            }
        }
        return materias;
    }
    
    public List<Integer> cargaCorrelativasById(Integer id) {
         List<Integer> correlativas = new ArrayList<>();
         Connection conexion = null;
         
         try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + this.dataBase);
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(this.leeCorrelativasQuery + id.toString());

            while(rs.next()) {
                correlativas.add(rs.getInt("correlativa1"));
                correlativas.add(rs.getInt("correlativa2"));
                correlativas.add(rs.getInt("correlativa3"));
            }
        } catch (SQLException exepcion) {
            System.out.println(exepcion.getMessage());
            
        } finally {
            try {
                if(conexion != null) {
                    conexion.close();
                }
            } catch (Exception exepcion) {
                System.out.println(exepcion.getMessage());
            }
        }
        return correlativas;
    }
    
    @Override
    public String toString() {
        return "\n" + "Materias: " + this.materias;
    }
}
