/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.DAO;

import com.unab.edu.Entidades.Profesor;
import com.unab.edu.conexionmysql.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ClsProfesor {
    
    ConexionBD cn = new ConexionBD();
    Connection conexion = cn.retornarConexion();

    public boolean LoguinProfesor(String usuario, String Pass) {
        ArrayList<Profesor> ListaProf = new ArrayList<>();
        ArrayList<Profesor> ListarContra = new ArrayList<>();
        try {
            CallableStatement st = conexion.prepareCall("call SP_S_LOGUINPROFESOR(?,?)");

            st.setString("profusuario", usuario);
            st.setString("profpass", Pass);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Profesor prof = new Profesor();
                prof.setUsuario(rs.getNString("Usuario"));
                prof.setPass(rs.getNString("Pass"));
                ListaProf.add(prof);
            }
            String usuariodebasedatos = null;
            String passdebasededatos = null;
            for (var iterador : ListaProf) {
                usuariodebasedatos = iterador.getUsuario();
                passdebasededatos = iterador.getPass();

            }

            CallableStatement st2 = conexion.prepareCall("call SP_S_CRIPSHA2(?)");
            st2.setString("PcripPass", Pass);
            ResultSet rs2 = st2.executeQuery();
            while (rs2.next()) {
                Profesor profcrip = new Profesor();

                profcrip.setPass(rs2.getNString("crip"));
                ListarContra.add(profcrip);
            }

            String passcrip = null;
            for (var iterador : ListarContra) {

                passcrip = iterador.getPass();

                Pass = passcrip;

            }
           
            
            if(usuariodebasedatos!=null &&passdebasededatos!=null ){
            if (usuariodebasedatos.equals(usuario) && passdebasededatos.equals(Pass)) {
                return true;
            }
            }
            conexion.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
}
