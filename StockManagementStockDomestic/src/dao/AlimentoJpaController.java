/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/// CLASSES PRÓPRIAS
import model.tiposMaterial.Alimento;
import exception.DAOException;
import java.util.ArrayList;
import model.Material;

/**
 * Classe controladora JPA do modelo Alimento
 * @see IMaterialDAO
 * @see Material
 */
public class AlimentoJpaController implements Serializable, IMaterialDAO {

    /// ATRIBUTOS ********************************************************************************
    
    private static AlimentoJpaController instance;
    private EntityManager entityManager;


    /// CONSTRUTOR *******************************************************************************

    private AlimentoJpaController() {
        this.entityManager = getEntityManager();
    }

    /// GETTERS **********************************************************************************
        
    public EntityManager getEntityManager() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");

        if (entityManager == null) {
          entityManager = factory.createEntityManager();
        }

         return entityManager;        
        
    }
    
    public static AlimentoJpaController getInstance(){
    	 
        if (instance == null){
            instance = new AlimentoJpaController();
        }
    
        return instance;
    }
    
    public int getAlimentoCount() {
    
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alimento> rt = cq.from(Alimento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /// MÉTODOS **********************************************************************************
    
    // INSERÇÃO
    public void create(Alimento clienteFisico) throws DAOException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(clienteFisico);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
        }
     }

    @Override
    public void adicionar(Material material) throws DAOException {
        create((Alimento) material ); 
    }

    // ALTERAÇÃO
    public void edit(Alimento clienteFisico)  throws DAOException {
     
    	try {
            entityManager.getTransaction().begin();
            entityManager.merge(clienteFisico);
            entityManager.getTransaction().commit();
         } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
         }
     }

    @Override
    public void alterar(Material materialAlterado) throws DAOException {
        edit((Alimento) materialAlterado ); 
    }

    // REMOÇÃO
    public void destroy(Alimento clienteFisico)throws DAOException {
        try {
            entityManager.getTransaction().begin();
            clienteFisico = entityManager.find(Alimento.class, clienteFisico.getId() );
            entityManager.remove(clienteFisico);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
        }
    }

    @Override
    public void remover(Material material) throws DAOException {
        destroy((Alimento) material ); 
    }
    
    // CONSULTA
    public Alimento findAlimento(String login) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alimento.class, login);
        } finally {
            em.close();
        }
    }
    
    @Override
    public Material consultar(String login) throws DAOException {
        Alimento clienteFisico = findAlimento(login);
        
        if( clienteFisico == null )
            throw new DAOException("Alimento nao encontrado");
		
        return clienteFisico;
    }
    
    // CONSULTA TODOS
    private List<Alimento> findAlimentoEntities() {
    	 return entityManager.createQuery("FROM " + Alimento.class.getName() ).getResultList();
     }
    
    @Override
    public List<Material> consultarTodos() throws DAOException {
        
        List<Alimento> clientes = findAlimentoEntities();
    	ArrayList<Material> materials = new ArrayList<Material>(); 
		
        if( clientes == null )
            throw new DAOException("Alimento nao encontrado");
		
        for( Alimento i : clientes) {
            materials.add(i);
        }
		
        return materials;    
    }
    
}
