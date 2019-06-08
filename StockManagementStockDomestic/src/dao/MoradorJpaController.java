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
import model.tiposUsuario.Morador;
import exception.DAOException;
import java.util.ArrayList;
import model.Usuario;

/**
 * Classe controladora JPA do modelo Morador
 * @see IUsuarioDAO
 * @see Usuario
 */
public class MoradorJpaController implements Serializable, IUsuarioDAO {

    /// ATRIBUTOS ********************************************************************************
    
    private static MoradorJpaController instance;
    private EntityManager entityManager;


    /// CONSTRUTOR *******************************************************************************

    private MoradorJpaController() {
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
    
    public static MoradorJpaController getInstance(){
    	 
        if (instance == null){
            instance = new MoradorJpaController();
        }
    
        return instance;
    }
    
    public int getMoradorCount() {
    
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Morador> rt = cq.from(Morador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /// MÉTODOS **********************************************************************************
    
    // INSERÇÃO
    public void create(Morador clienteFisico) throws DAOException {
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
    public void adicionar(Usuario usuario) throws DAOException {
        create((Morador) usuario ); 
    }

    // ALTERAÇÃO
    public void edit(Morador clienteFisico)  throws DAOException {
     
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
    public void alterar(Usuario usuarioAlterado) throws DAOException {
        edit((Morador) usuarioAlterado ); 
    }

    // REMOÇÃO
    public void destroy(Morador clienteFisico)throws DAOException {
        try {
            entityManager.getTransaction().begin();
            clienteFisico = entityManager.find(Morador.class, clienteFisico.getId() );
            entityManager.remove(clienteFisico);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
        }
    }

    @Override
    public void remover(Usuario usuario) throws DAOException {
        destroy((Morador) usuario ); 
    }
    
    // CONSULTA
    public Morador findMorador(String login) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Morador.class, login);
        } finally {
            em.close();
        }
    }
    
    @Override
    public Usuario consultar(String login) throws DAOException {
        Morador clienteFisico = findMorador(login);
        
        if( clienteFisico == null )
            throw new DAOException("Funcionario nao encontrado");
		
        return clienteFisico;
    }
    
    // CONSULTA TODOS
    private List<Morador> findMoradorEntities() {
    	 return entityManager.createQuery("FROM " + Morador.class.getName() ).getResultList();
     }
    
    @Override
    public List<Usuario> consultarTodos() throws DAOException {
        
        List<Morador> clientes = findMoradorEntities();
    	ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); 
		
        if( clientes == null )
            throw new DAOException("Morador nao encontrado");
		
        for( Morador i : clientes) {
            usuarios.add(i);
        }
		
        return usuarios;    
    }
    
}
