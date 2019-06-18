/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


/// CLASSES PRÓPRIAS
import model.tiposMaterial.Eletrodomestico;
import exception.DAOException;
import model.Material;

/**
 * Classe controladora JPA do modelo Eletrodomestico
 * @see IMaterialDAO
 * @see Material
 */
public class EletrodomesticoJpaController implements Serializable, IMaterialDAO {

    /// ATRIBUTOS ********************************************************************************
    
    private static EletrodomesticoJpaController instance;
    private EntityManager entityManager;


    /// CONSTRUTOR *******************************************************************************

    private EletrodomesticoJpaController() {
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
    
    public static EletrodomesticoJpaController getInstance(){
    	 
        if (instance == null){
            instance = new EletrodomesticoJpaController();
        }
    
        return instance;
    }
    
    public int getEletrodomesticoCount() {
    
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Eletrodomestico> rt = cq.from(Eletrodomestico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /// MÉTODOS **********************************************************************************
    
    // INSERÇÃO
    public void create(Eletrodomestico clienteFisico) throws DAOException {
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
        create((Eletrodomestico) material ); 
    }

    // ALTERAÇÃO
    public void edit(Eletrodomestico clienteFisico)  throws DAOException {
     
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
        edit((Eletrodomestico) materialAlterado ); 
    }

    // REMOÇÃO
    public void destroy(Eletrodomestico clienteFisico)throws DAOException {
        try {
            entityManager.getTransaction().begin();
            clienteFisico = entityManager.find(Eletrodomestico.class, clienteFisico.getId() );
            entityManager.remove(clienteFisico);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
        }
    }

    @Override
    public void remover(Material material) throws DAOException {
        destroy((Eletrodomestico) material ); 
    }
    
    // CONSULTA
    public Eletrodomestico findEletrodomestico(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Eletrodomestico.class, id);
        } finally {
            em.close();
        }
    }
    
    @Override
    public Material consultar(long id) {
        Eletrodomestico clienteFisico = findEletrodomestico(id);
        
        return clienteFisico;
    }
    
    // CONSULTA TODOS
    private List<Eletrodomestico> findEletrodomesticoEntities() {
    	 return entityManager.createQuery("FROM " + Eletrodomestico.class.getName() ).getResultList();
     }
    
    @Override
    public List<Material> consultarTodos() {
        
        List<Eletrodomestico> clientes = findEletrodomesticoEntities();
    	ArrayList<Material> materials = new ArrayList<Material>(); 
		
        for( Eletrodomestico i : clientes) {
            materials.add(i);
        }
		
        return materials;    
    }
    
}
