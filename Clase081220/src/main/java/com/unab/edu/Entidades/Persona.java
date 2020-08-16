/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.Entidades;

import lombok.Data;

/**
 *
 * @author Usuario
 */
@Data
public class Persona {
    private int idPersona;
    private String Nombre;
    private String Apellido;
    private int Edad;
    private String Sexo;
}
