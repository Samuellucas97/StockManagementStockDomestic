package service;

/// CLASSES PRÓPRIAS
import service.raqueamento.IRankingMaterialStrategy;
import model.Material;
import dao.IMaterialDAO;
import exception.ServiceException;

import java.util.List;
import service.validacao.ValidacaoMaterial;

/**
 * Classe que representa as funcionalidades desempenhadas pelo material 
 * @see Material
 * @see IMaterialDAO
 */
public abstract class MaterialService {

    /// ATRIBUTOS ********************************************************************************
    
    protected IMaterialDAO materialDAO;
    protected ValidacaoMaterial validacaoMaterial;

    /// MÉTODOS **********************************************************************************
    
    /**
     * Adiciona um material caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param material   Material a ser adicionado
     * @return Um string contendo ok caso bem sucedido
     * @throws exception.ServiceException
     */
    public abstract String adicionar(Material material) throws ServiceException;
    
    /**
     * Altera os dados de um material caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param material   Material a ser alterado
     * @param materialAlterado Material alterado
     * @return Um string contendo ok caso bem sucedido
     * @throws exception.ServiceException
     */
    public abstract String alterar(Material material, Material materialAlterado) throws ServiceException;
    /**
     * Remove um material caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param material   Material a ser removido
     * @return Um string contendo ok caso bem sucedido
     * @throws exception.ServiceException
     */
    public abstract String remover(Material material) throws ServiceException;
    
    /**
     * Consulta um material caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param material   Material a ser consultado
     * @return Um string contendo dados do material caso bem sucedido
     * @throws exception.ServiceException
     */
    public abstract String consultar(Material material) throws ServiceException;
    
    /**
     * Consulta todos materiais caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @return Lista de strings contendo dados dos materiais caso bem sucedido
     * @throws exception.ServiceException
     */
    public abstract List<String> consultarTodos() throws ServiceException;
    
    /**
     * Consulta por meio de parametros caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param params    Parametros da busca
     * @param keys  Chaves da busca
     * @return Um string contendo ok caso bem sucedido
     * @throws exception.ServiceException
     */
    public abstract List<String> consultaEspecifica(List<String> params, List<String> keys) throws ServiceException;
    
    /**
     * Ordena um material caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param rankingMaterial
     * @return Um string contendo ok caso bem sucedido
     * @throws exception.ServiceException
     */
    public abstract List<String> ranquear(IRankingMaterialStrategy rankingMaterial) throws ServiceException;
    
    /**
     * Valida um material caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param material   Material a ser adicionado
     * @return Um string contendo ok caso bem sucedido
     * @throws exception.ServiceException
     */
    public abstract String validacao(Material material) throws ServiceException;
    
}
