package dao;

import model.Material;
import exception.DAOException;
import java.util.List;

public interface IMaterialDAO {
    
    /**
     * Adiciona o usuário no Banco de dados caso seja bem sucedido. 
     * Caso contrário, envia uma exceção
     * @param material   Usuário a ser adicionado
     * @throws DAOException 
     */
    public void adicionar(Material material) throws DAOException;
    
    /**
     * Altera o usuário no Banco de dados caso seja bem sucedido. 
     * Caso contrário, envia uma exceção
     * @param materialAlterado    Usuário a ser alterado
     * @throws DAOException 
     */    
    public void alterar(Material materialAlterado) throws DAOException;

    /**
     * Remove o usuário no Banco de dados caso seja bem sucedido. 
     * Caso contrário, envia uma exceção
     * @param material   Usuário a ser removido
     * @throws DAOException 
     */
    public void remover(Material material) throws DAOException;
    
    /**
     * Consulta o usuário no Banco de dados e retorna-o caso seja bem sucedido. 
     * Caso contrário, envia uma exceção
     * @param login Login do usuário consultado
     * @return Material consultado caso seja bem sucedido
     * @throws DAOException 
     */
    public Material consultar(String login) throws DAOException;

    /**
     * Consulta todos os usuário no Banco de dados e retorna-o caso seja bem sucedido. 
     * Caso contrário, envia uma exceção
     * @return Lista contendo todos os usuários cadastrados no Banco de dados
     * @throws DAOException 
     */
    public List<Material> consultarTodos() throws DAOException;

}
