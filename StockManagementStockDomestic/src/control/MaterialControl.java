/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;

/// CLASSE PRÓPRIA
import service.MaterialService;
import exception.ControlException;

/**
 * Classe abstrata controladora de tela material
 * @see MaterialService
 * @see ControlException
 */
public abstract class MaterialControl {
    
    /// ATRIBUTOS ********************************************************************************
    
    protected MaterialService materialService;
    
    /// CONSTRUTOR *******************************************************************************
    
    public MaterialControl( ){ /** vazio */  }
    
    /// MÉTODOS **********************************************************************************
    
    /**
     * Recebe atributos de material para cadastrar 
     * @param atributosMaterial Atributos do material a ser cadastrado
     * @return  Ok caso bem sucedido. Caso contrário lança-se uma exceção
     * @throws ControlException 
     */
    public abstract String adicionar(List<String> atributosMaterial) throws ControlException;
    
    /**
     * Recebe atributos de material para remoção 
     * @param atributosMaterial Atributos do material a ser removido
     * @return  Ok caso bem sucedido. Caso contrário lança-se uma exceção
     * @throws ControlException 
     */
    public abstract String remover(List<String> atributosMaterial) throws ControlException;
    
    /**
     * Recebe atributos de material para alteração
     * @param atributosMaterial Atributos do material a ser alterado
     * @return  Ok caso bem sucedido. Caso contrário lança-se uma exceção
     * @throws ControlException 
     */
    public abstract String alterar(List<String> atributosMaterial) throws ControlException;
    
    /**
     * Consulta de forma específica
     * @param nomesDosAtributos Nomes dos atributos do material a ser consultado
     * @param atributosMaterial Atributos do material a ser consultado
     * @return  Lista de lista de string contendo todos os atributos dos materiais 
     * encontrados
     * @throws ControlException 
     */
    public abstract List<List<String>> consultar(List<String> nomesDosAtributos, List<String> atributosMaterial) throws ControlException;
    
    /**
     * Devolve-se uma lista de listas de string contendo todos os atributos dos materiais cadastrados 
     * caso haja. Entretanto, se não houve material cadastrado, retorna-se uma exceção
     * @return  Lista de listas contendo atributos de todos usuário
     * @throws ControlException 
     */
    public abstract List<List<String>> consultarTodos() throws ControlException;
    
    /**
     * Devolve-se uma lista de listas de string contendo todos os atributos dos materiais 
     * cadastrados ordenados de acordo com a escolha caso haja. Entretanto, se não houve 
     * material cadastrado, retorna-se uma exceção
     * @param tipoDeRankingMaterial Tipo de ranqueamento de material
     * @return  Lista de listas contendo atributos de todos materiais ordenados
     * @throws ControlException 
     */
    public abstract List<String> ranquear(String tipoDeRankingMaterial) throws ControlException;
    
}