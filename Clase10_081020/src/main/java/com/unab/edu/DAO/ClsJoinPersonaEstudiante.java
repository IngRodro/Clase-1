/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.DAO;

import com.unab.edu.Entidades.Estudiante;
import com.unab.edu.conexionmysql.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ClsJoinPersonaEstudiante {

    ConexionBD cn = new ConexionBD();
    Connection con = cn.retornarConexion();

    public ArrayList<Estudiante> MostrarJoinEstudiantePersona() {
        ArrayList<Estudiante> lista = new ArrayList<>();

        try {
            CallableStatement st = con.prepareCall("call SP_S_JOINPERSONAESTUDIANTE");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Estudiante es = new Estudiante();
                es.setNombre(rs.getNString("nombre"));
                es.setApellido(rs.getNString("apellido"));
                es.setMatricula(rs.getInt("matricula"));

                lista.add(es);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        return lista;
    }
}
