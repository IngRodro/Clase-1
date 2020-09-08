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
public class ClsEstudiante {
    ConexionBD cn = new ConexionBD();
    Connection conexion = cn.retornarConexion();

    public boolean LoguinEstudiante(String usuario, String Pass) {
        ArrayList<Estudiante> ListaUsuarios = new ArrayList<>();

        try {
            CallableStatement st = conexion.prepareCall("call SP_S_LOGUIESTUDIANTE(?,?)");
            st.setString("pusuario", usuario);
            st.setString("ppass", Pass);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Estudiante es = new Estudiante();
                es.setUsu(rs.getNString("USU"));
                es.setPass(rs.getNString("PASS"));

                ListaUsuarios.add(es);
            }
            String usuariodebasedatos = null;
            String passdebasededatos = null;
            for(var iterador:ListaUsuarios){
            usuariodebasedatos = iterador.getUsu();
            passdebasededatos = iterador.getPass();
            }
            if(usuariodebasedatos.equals(usuario) && passdebasededatos.equals(Pass)){
            return true;
            }
            else{}
            conexion.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        return false;
    }
    public ArrayList<Estudiante> MostrarEstudiantes() {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_S_Estudiante()");
            ResultSet rs = Statement.executeQuery();
            while (rs.next()) {
                Estudiante es = new Estudiante();
                es.setId(rs.getInt("idestudiante"));
                es.setIdPersona(rs.getInt("idPersona"));
                es.setNombre(rs.getString("nombre"));
                es.setMatricula(rs.getInt("matricula"));
                es.setUsu(rs.getString("USU"));
                es.setPass(rs.getString("PASS"));
                es.setNIE(rs.getInt("NIE"));
                estudiantes.add(es);
            }
            conexion.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return estudiantes;
    }
    
    public void AgregarEstudiantes(Estudiante es) {
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_I_Estudiante(?,?,?,?,?)");
            Statement.setInt("EMatricula", es.getMatricula());
            Statement.setInt("EidPersona", es.getIdPersona());
            Statement.setString("EUsuario", es.getUsu());
            Statement.setString("EPass", es.getPass());
            Statement.setInt("ENIE", es.getNIE());
            Statement.execute();
            JOptionPane.showMessageDialog(null, "Estudiante Guardado");
            conexion.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void BorrarEstudiantes(Estudiante es) {
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_D_Estudiante(?)");
            Statement.setInt("EIdEstudiante", es.getId());
            Statement.execute();
            JOptionPane.showMessageDialog(null, "Estudiante Eliminado");
            conexion.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
     public void ActualizarEstudiantes(Estudiante es) {
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_U_Estudiante(?,?,?,?,?,?)");
            Statement.setInt("EidEstudiante", es.getId());
            Statement.setInt("EMatricula", es.getMatricula());
            Statement.setInt("EidPersona", es.getIdPersona());
            Statement.setString("EUsuario", es.getUsu());
            Statement.setString("EPass", es.getPass());
            Statement.setInt("ENIE", es.getNIE());
            Statement.execute();
            JOptionPane.showMessageDialog(null, "Estudiante Actualizado");
            conexion.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
}
