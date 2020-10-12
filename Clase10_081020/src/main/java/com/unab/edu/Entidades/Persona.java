/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.Entidades;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author Usuario
 */
@Data
public class Persona {
    protected int idPersona;
    protected String Nombre;
    protected String Apellido;
    protected int Edad;
    protected String Sexo;
    protected Date Fecha;
}
