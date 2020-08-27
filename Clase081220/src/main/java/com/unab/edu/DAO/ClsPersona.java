/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.DAO;

import com.unab.edu.Entidades.Persona;
import com.unab.edu.conexionmysql.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ClsPersona {

    ConexionBD claseConectar = new ConexionBD();
    Connection conectar = claseConectar.retornarConexion();

    public ArrayList<Persona> MostrarPersona() {
        ArrayList<Persona> Personas = new ArrayList<>();
        try {
            CallableStatement Statement = conectar.prepareCall("call SP_S_Persona()");
            ResultSet resultadodeConsulta = Statement.executeQuery();
            while (resultadodeConsulta.next()) {
                Persona persona = new Persona();
                persona.setIdPersona(resultadodeConsulta.getInt("idPersona"));
                persona.setNombre(resultadodeConsulta.getString("Nombre"));
                persona.setApellido(resultadodeConsulta.getString("Apellido"));
                persona.setEdad(resultadodeConsulta.getInt("Edad"));
                persona.setSexo(resultadodeConsulta.getString("Sexo"));
                Personas.add(persona);
            }
            conectar.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return Personas;
    }

    public void AgregarPersonas(Persona per) {
        try {
            CallableStatement Statement = conectar.prepareCall("call SP_I_Persona(?,?,?,?)");
            Statement.setString("PNombre", per.getNombre());
            Statement.setString("PApellido", per.getApellido());
            Statement.setInt("PEdad", per.getEdad());
            Statement.setString("PSexo", per.getSexo());
            Statement.execute();
            JOptionPane.showMessageDialog(null, "Persona Guardada");
            conectar.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void BorrarPersonas(Persona per) {
        try {
            CallableStatement Statement = conectar.prepareCall("call SP_D_Persona(?)");
            Statement.setInt("PIdPersona", per.getIdPersona());
            Statement.execute();
            JOptionPane.showMessageDialog(null, "Persona Eliminada");
            conectar.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
     public void ActualizarPersonas(Persona per) {
        try {
            CallableStatement Statement = conectar.prepareCall("call SP_U_Persona(?,?,?,?,?)");
            Statement.setInt("PIdPersona", per.getIdPersona());
            Statement.setString("PNombre", per.getNombre());
            Statement.setString("PApellido", per.getApellido());
            Statement.setInt("PEdad", per.getEdad());
            Statement.setString("PSexo", per.getSexo());
            Statement.execute();
            JOptionPane.showMessageDialog(null, "Persona Actualizada");
            conectar.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
}
