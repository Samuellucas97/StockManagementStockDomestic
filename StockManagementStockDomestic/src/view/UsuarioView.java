/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.UsuarioControl;

/**
 * Representa uma tela de material genérico
 * @see UsuarioControl
 */
public abstract class UsuarioView {

    /// ATRIBUTOS ********************************************************************************
    
    protected UsuarioControl materialControl;

    /// CONSTRUTOR *******************************************************************************
    
     public UsuarioView( ){ /** vazio */  }
    
    /// MÉTODOS **********************************************************************************
    
     /**
      * Apresenta a tela de material
      */
    public abstract void apresentacao();
     
}
