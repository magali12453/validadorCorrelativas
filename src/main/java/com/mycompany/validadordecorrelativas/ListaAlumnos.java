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
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author magalib
 */

public class ListaAlumnos {
    private List<Alumno> alumnos;
    private ListaMaterias materias;
    private final String dataBase = "./database.db";
    private final String leeAlumnosQuery = "SELECT"
            + " idAlumno,"
            + " legajo,"
            + " nombre,"
            + " materia1,"
            + " materia2,"
            + " materia3,"
            + " materia4,"
            + " isIntroduccionApproved,"
            + " isFilosofiaApproved,"
            + " isEpistemologiaApproved,"
            + " isGRAP1Approved,"
            + " isHistoriaApproved,"
            + " isBiologiaApproved,"
            + " isInvestigacionApproved,"
            + " isAntorpologiaApproved,"
            + " isSistemas1Approved,"
            + " isPsicoanaliticaApproved,"
            + " isDesarrolloApproved,"
            + " isSociologiaApproved,"
            + " isSistemas2Approved,"
            + " isProblemasApproved,"
            + " isNeuroPsicologiaApproved,"
            + " isGRAP2Approved"
            + " FROM alumnos";
    
    public ListaAlumnos() {
        this.materias = new ListaMaterias();
        this.alumnos = cargaAlumnos();
    }
    
    private List<Alumno> cargaAlumnos() {
        List<Alumno> alumnosloc = new ArrayList<>();
        List<Materia> materiasInscripcion = new ArrayList<>();
        Map<String, Boolean> materiasAprobadas = new HashMap<>();
        
        Connection conexion = null;
        
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + this.dataBase);
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(this.leeAlumnosQuery);
            
            while(rs.next()) {
                materiasInscripcion.add(materias.getMateria(rs.getInt("materia1")));
                materiasInscripcion.add(materias.getMateria(rs.getInt("materia2")));
                materiasInscripcion.add(materias.getMateria(rs.getInt("materia3")));
                materiasInscripcion.add(materias.getMateria(rs.getInt("materia4")));
                
                materiasAprobadas.put("isIntroduccionApproved", rs.getBoolean(("isIntroduccionApproved")));
                materiasAprobadas.put("isFilosofiaApproved", rs.getBoolean("isFilosofiaApproved"));
                materiasAprobadas.put("isEpistemologiaApproved", rs.getBoolean("isEpistemologiaApproved"));
                materiasAprobadas.put("isGRAP1Approved", rs.getBoolean("isGRAP1Approved"));
                materiasAprobadas.put("isHistoriaApproved", rs.getBoolean("isHistoriaApproved"));
                materiasAprobadas.put("isBiologiaApproved", rs.getBoolean("isBiologiaApproved"));
                materiasAprobadas.put("isInvestigacionApproved", rs.getBoolean("isInvestigacionApproved"));
                materiasAprobadas.put("isAntorpologiaApproved", rs.getBoolean("isAntorpologiaApproved"));
                materiasAprobadas.put("isSistemas1Approved", rs.getBoolean("isSistemas1Approved"));
                materiasAprobadas.put("isPsicoanaliticaApproved", rs.getBoolean("isPsicoanaliticaApproved"));
                materiasAprobadas.put("isDesarrolloApproved", rs.getBoolean("isDesarrolloApproved"));
                materiasAprobadas.put("isSociologiaApproved", rs.getBoolean("isSociologiaApproved"));
                materiasAprobadas.put("isSistemas2Approved", rs.getBoolean("isSistemas2Approved"));
                materiasAprobadas.put("isProblemasApproved", rs.getBoolean("isProblemasApproved"));
                materiasAprobadas.put("isNeuroPsicologiaApproved", rs.getBoolean("isNeuroPsicologiaApproved"));
                materiasAprobadas.put("isGRAP2Approved", rs.getBoolean("isGRAP2Approved"));
                
                Alumno alumno = new Alumno(
                        rs.getInt("idAlumno"),
                        rs.getInt("legajo"),
                        rs.getString("nombre").replace("\"", ""),
                        materiasInscripcion,
                        materiasAprobadas);
                alumnosloc.add(alumno);
                
                materiasInscripcion.clear();
                materiasAprobadas.clear();
            }
        } catch (SQLException excepcion) {
            System.out.println(excepcion.getMessage());
        } finally {
            try {
                if(conexion != null) {
                    conexion.close();
                }
            } catch (Exception excepcion) {
                System.out.println(excepcion.getMessage());
            }
        }
        return alumnosloc;
    }
    
    
            
    /*
        List<Integer> materiasInscripcion = new ArrayList<>();
        Map<String, Boolean> materiasAprobadas = new HashMap<>();
                
    */        
    
    @Override
    public String toString() {
        return "\n" + "Alumnos: " + this.alumnos;
    }
    
    /* Creacion de tabla de alumnos
    CREATE TABLE "alumnos" (
	"idAlumno"	INTEGER UNIQUE,
	"nombre"	TEXT,
	"materia1"	INTEGER,
	"materia2"	INTEGER,
	"materia3"	INTEGER,
	"materia4"	INTEGER,
	"isIntroduccionApproved"	TEXT,
	"isFilosofiaApproved"	TEXT,
	"isEpistemologiaApproved"	TEXT,
	"isGRAPApproved"	TEXT,
	"isHistoriaApproved"	TEXT,
	"isBiologiaApproved"	TEXT,
	"isInvestigacionApproved"	TEXT,
	"isAntorpologiaApproved"	TEXT,
	"isSistemas1Approved"	TEXT,
	"isPsicoanaliticaApproved"	TEXT,
	"isDesarrolloApproved"	TEXT,
	"isSociologiaApproved"	TEXT,
	"isSistemas2Approved"	TEXT,
	"isProblemasApproved"	TEXT,
	"isNeuroPsicologiaApproved"	TEXT,
	"isGRAP2Approved"	TEXT,
	FOREIGN KEY("materia4") REFERENCES "materias"("idMateria"),
	PRIMARY KEY("idAlumno" AUTOINCREMENT),
	FOREIGN KEY("materia2") REFERENCES "materias"("idMateria"),
	FOREIGN KEY("materia1") REFERENCES "materias"("idMateria"),
	FOREIGN KEY("materia3") REFERENCES "materias"("idMateria")
    );
    */
}
