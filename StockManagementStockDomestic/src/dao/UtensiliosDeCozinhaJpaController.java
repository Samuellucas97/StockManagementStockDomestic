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
import model.tiposMaterial.UtensiliosDeCozinha;
import exception.DAOException;
import model.Material;

/**
 * Classe controladora JPA do modelo UtensiliosDeCozinha
 * @see IMaterialDAO
 * @see Material
 */
public class UtensiliosDeCozinhaJpaController implements Serializable, IMaterialDAO {

    /// ATRIBUTOS ********************************************************************************
    
    private static UtensiliosDeCozinhaJpaController instance;
    private EntityManager entityManager;


    /// CONSTRUTOR *******************************************************************************

    private UtensiliosDeCozinhaJpaController() {
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
    
    public static UtensiliosDeCozinhaJpaController getInstance(){
    	 
        if (instance == null){
            instance = new UtensiliosDeCozinhaJpaController();
        }
    
        return instance;
    }
    
    public int getUtensiliosDeCozinhaCount() {
    
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UtensiliosDeCozinha> rt = cq.from(UtensiliosDeCozinha.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /// MÉTODOS **********************************************************************************
    
    // INSERÇÃO
    public void create(UtensiliosDeCozinha clienteFisico) throws DAOException {
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
        create((UtensiliosDeCozinha) material ); 
    }

    // ALTERAÇÃO
    public void edit(UtensiliosDeCozinha clienteFisico)  throws DAOException {
     
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
        edit((UtensiliosDeCozinha) materialAlterado ); 
    }

    // REMOÇÃO
    public void destroy(UtensiliosDeCozinha clienteFisico)throws DAOException {
        try {
            entityManager.getTransaction().begin();
            clienteFisico = entityManager.find(UtensiliosDeCozinha.class, clienteFisico.getId() );
            entityManager.remove(clienteFisico);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
        }
    }

    @Override
    public void remover(Material material) throws DAOException {
        destroy((UtensiliosDeCozinha) material ); 
    }
    
    // CONSULTA
    public UtensiliosDeCozinha findUtensiliosDeCozinha(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UtensiliosDeCozinha.class, id);
        } finally {
            em.close();
        }
    }
    
    @Override
    public Material consultar(long id) {
        UtensiliosDeCozinha clienteFisico = findUtensiliosDeCozinha(id);
        
        return clienteFisico;
    }
    
    // CONSULTA TODOS
    private List<UtensiliosDeCozinha> findUtensiliosDeCozinhaEntities() {
    	 return entityManager.createQuery("FROM " + UtensiliosDeCozinha.class.getName() ).getResultList();
     }
    
    @Override
    public List<Material> consultarTodos() {
        
        List<UtensiliosDeCozinha> clientes = findUtensiliosDeCozinhaEntities();
    	ArrayList<Material> materials = new ArrayList<Material>(); 
		
        for( UtensiliosDeCozinha i : clientes) {
            materials.add(i);
        }
		
        return materials;    
    }
    
}
