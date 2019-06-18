/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 * Representa uma exceção da camada control
 * @see Exception
 */
public class ControlException extends Exception{
    public ControlException(String mensagem){
        super( mensagem );
    }
}