package service;

/// CLASSES PRÓPRIAS
import service.raqueamento.IRankingUsuarioStrategy;
import model.Usuario;
import dao.IUsuarioDAO;
import exception.ServiceException;

import java.util.List;
import service.validacao.ValidacaoUsuario;

/**
 * Classe que representa as funcionalidades desempenhadas pelo usuario 
 * @see Usuario
 * @see IUsuarioDAO
 */
public abstract class UsuarioService {

    /// ATRIBUTOS ********************************************************************************
    
    protected IUsuarioDAO usuarioDAO;
    protected ValidacaoUsuario validacaoUsuario;
    
    /// MÉTODOS **********************************************************************************
    
    /**
     * Adiciona um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param usuario   Usuario a ser adicionado
     * @return Um string contendo ok caso bem sucedido
     * @throws exception.ServiceException
     */
    public abstract String adicionar(Usuario usuario) throws ServiceException;
    
    /**
     * Altera os dados de um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param usuario   Usuario a ser alterado
     * @param usuarioAlterado Usuario alterado
     * @return Um string contendo ok caso bem sucedido
     * @throws exception.ServiceException
     */
    public abstract String alterar(Usuario usuario, Usuario usuarioAlterado) throws ServiceException;
    /**
     * Remove um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param usuario   Usuario a ser removido
     * @return Um string contendo ok caso bem sucedido
     * @throws exception.ServiceException
     */
    public abstract String remover(Usuario usuario) throws ServiceException;
    
    /**
     * Consulta um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao.
     * @param usuario   Usuario a ser consultado.
     * @return Um string contendo dados do usuario caso bem sucedido.
     * @throws exception.ServiceException
     */
    public abstract String consultar(Usuario usuario) throws ServiceException;
    
    /**
     * Consulta todos usuarios caso bem sucedido. Caso contrario, lanca-se uma exceção. 
     * @return Lista de strings contendo dados dos usuarios caso bem sucedido.
     * @throws exception.ServiceException
     */
    public abstract List<String> consultarTodos() throws ServiceException;
    
    /**
     * Consulta usuarios por meio de parametros caso bem sucedido. Caso contrario, lanca-se uma exceção. 
     * @param params    Parametros da busca
     * @param keys  Chaves da busca
     * @return Lista de strings contendo dados dos usuarios caso bem sucedido.
     * @throws exception.ServiceException
     */
    public abstract List<String> consultaEspecifica(List<String> params, List<String> keys) throws ServiceException;
    
    /**
     * Ordena um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param rankingUsuario
     * @return Um string contendo ok caso bem sucedido
     * @throws exception.ServiceException
     */
    public abstract List<String> ranquear(IRankingUsuarioStrategy rankingUsuario) throws ServiceException;
    
    /**
     * Adiciona um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param usuario
     * @return Um string contendo ok caso bem sucedido
     * @throws exception.ServiceException
     */
    public abstract String verNotificacao(Usuario usuario) throws ServiceException;
    
    /**
     * Notifica todos os usuarios
     * @param notificacao   Mensagem de notificacao
     * @param usuario
     * @throws ServiceException 
     */
    public abstract void notificar(String notificacao, Usuario usuario) throws ServiceException;
    
}
