package dao;

import java.util.List;

/// CLASSES PRÓPRIAS
import model.Usuario;
import exception.DAOException;

/**
 * Interface para as classes controladoras DAOs dos modelos subclasses de Usuario
 * @see DAOException
 * @see Usuario
 */
public interface IUsuarioDAO {
    
    /**
     * Adiciona o usuário no Banco de dados caso seja bem sucedido. 
     * Caso contrário, envia uma exceção
     * @param usuario   Usuário a ser adicionado
     * @throws DAOException 
     */
    public void adicionar(Usuario usuario) throws DAOException;
    
    /**
     * Altera o usuário no Banco de dados caso seja bem sucedido. 
     * Caso contrário, envia uma exceção
     * @param usuarioAlterado    Usuário a ser alterado
     * @throws DAOException 
     */    
    public void alterar(Usuario usuarioAlterado) throws DAOException;

    /**
     * Remove o usuário no Banco de dados caso seja bem sucedido. 
     * Caso contrário, envia uma exceção
     * @param usuario   Usuário a ser removido
     * @throws DAOException 
     */
    public void remover(Usuario usuario) throws DAOException;
    
    /**
     * Consulta o usuário no Banco de dados e retorna-o caso seja bem sucedido. 
     * Caso contrário, envia uma exceção
     * @param login Login do usuário consultado
     * @return Usuario consultado caso seja bem sucedido
     * @throws DAOException 
     */
    public Usuario consultar(String login) throws DAOException;

    /**
     * Consulta todos os usuário no Banco de dados e retorna-o caso seja bem sucedido. 
     * Caso contrário, envia uma exceção
     * @return Lista contendo todos os usuários cadastrados no Banco de dados
     * @throws DAOException 
     */
    public List<Usuario> consultarTodos() throws DAOException;

}
