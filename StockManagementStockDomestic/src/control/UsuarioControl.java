/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;

/// CLASSE PRÓPRIA
import service.UsuarioService;
import exception.ControlException;

/**
 * Classe abstrata controladora de tela usuario
 * @see UsuarioService
 * @see ControlException
 */
public abstract class UsuarioControl {
    
    /// ATRIBUTOS ********************************************************************************
    
    protected UsuarioService usuarioService;
    
    /// CONSTRUTOR *******************************************************************************
    
    public UsuarioControl( ){ /** vazio */  }
    
    /// MÉTODOS **********************************************************************************
    
    /**
     * Recebe atributos de usuario para cadastrar 
     * @param atributosUsuario Atributos do usuario a ser cadastrado
     * @return  Ok caso bem sucedido. Caso contrário lança-se uma exceção
     * @throws ControlException 
     */
    public abstract String adicionar(List<String> atributosUsuario) throws ControlException;
    
    /**
     * Recebe atributos de usuario para remoção 
     * @param atributosUsuario Atributos do usuario a ser removido
     * @return  Ok caso bem sucedido. Caso contrário lança-se uma exceção
     * @throws ControlException 
     */
    public abstract String remover(List<String> atributosUsuario) throws ControlException;
    
    /**
     * Recebe atributos de usuario para alteração
     * @param atributosUsuario Atributos do usuario a ser alterado
     * @return  Ok caso bem sucedido. Caso contrário lança-se uma exceção
     * @throws ControlException 
     */
    public abstract String alterar(List<String> atributosUsuario) throws ControlException;
    
    /**
     * Consulta de forma específica
     * @param nomesDosAtributos Nomes dos atributos do usuario a ser consultado
     * @param atributosUsuario Atributos do usuario a ser consultado
     * @return  Lista de lista de string contendo todos os atributos dos materiais 
     * encontrados
     * @throws ControlException 
     */
    public abstract List<List<String>> consultar(List<String> nomesDosAtributos, List<String> atributosUsuario) throws ControlException;
    
    /**
     * Devolve-se uma lista de listas de string contendo todos os atributos dos materiais cadastrados 
     * caso haja. Entretanto, se não houve usuario cadastrado, retorna-se uma exceção
     * @return  Lista de listas contendo atributos de todos usuário
     * @throws ControlException 
     */
    public abstract List<List<String>> consultarTodos() throws ControlException;
    
    /**
     * Devolve-se uma lista de listas de string contendo todos os atributos dos materiais 
     * cadastrados ordenados de acordo com a escolha caso haja. Entretanto, se não houve 
     * usuario cadastrado, retorna-se uma exceção
     * @param tipoDeRankingUsuario Tipo de ranqueamento de usuario
     * @return  Lista de listas contendo atributos de todos materiais ordenados
     * @throws ControlException 
     */
    public abstract List<String> ranquear(String tipoDeRankingUsuario) throws ControlException;
    
}