/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/// CLASSES PROPRIAS
import dao.EletrodomesticoJpaController;
import exception.DAOException;
import exception.ServiceException;
import java.util.ArrayList;
import java.util.Iterator;
import model.Material;
import service.raqueamento.IRankingMaterialStrategy;
import service.validacao.ValidacaoEletrodomestico;
import model.tiposMaterial.Eletrodomestico;

/**
 * Representa a operações que ocorre sobre um eletrodomestico
 * @see MaterialService
 */
public class EletrodomesticoService extends MaterialService{

    /// CONSTRUTOR *******************************************************************************
    
    public EletrodomesticoService(){
        this.materialDAO = EletrodomesticoJpaController.getInstance();
        this.validacaoMaterial = new ValidacaoEletrodomestico();
    }
    
    /// MÉTODOS **********************************************************************************
    
    @Override
    public String adicionar(Material material) throws ServiceException {
    
        int cont = 3;
        while (cont > 0){
            --cont;
            try {
                validacaoMaterial.validacao(material);
                Eletrodomestico eletrodomestico = (Eletrodomestico) material;
                materialDAO.adicionar(eletrodomestico);
                break;
            } catch (DAOException ex) {
                if( cont == 0)
                    throw new ServiceException( "Operação não concluida!" );
            }
        }
        
        return "OK";
    }

    @Override
    public String alterar(Material material, Material materialAlterado) throws ServiceException {

        int cont = 3;
        while (cont > 0){
            --cont;
            try {
                validacaoMaterial.validacao(material);  /// Verifica se existe e se houve problemna na passagem de informacao
                validacaoMaterial.validacao(materialAlterado);   /// Verifica se existe e se houve problemna na passagem de informacao                   
                Eletrodomestico eletrodomesticoAlterado = (Eletrodomestico) materialAlterado;
                materialDAO.alterar(eletrodomesticoAlterado);
                break;
            } catch (DAOException ex) {
                if( cont == 0)
                    throw new ServiceException( "Operação não concluida!" );
            }
        }
        
        return "OK";
    }

    @Override
    public String remover(Material material) throws ServiceException {

        int cont = 3;

        if (!material.getClass().equals(Eletrodomestico.class)) 
            throw new ServiceException("Tipo de material inválido!");

        Eletrodomestico eletrodomestico = (Eletrodomestico) material;

        try {
            validacaoMaterial.validacao(material);
            throw new ServiceException("Material não existente!");
        } 
        catch(ServiceException ex){
            if(ex.getMessage().equals("Material existente!")){
                while (cont > 0){
                    cont--;
                    try {                                
                        materialDAO.remover(eletrodomestico);
                        break;
                    } catch (DAOException ex1) {
                        if(cont == 0)
                            throw new ServiceException( "Operação não concluida!" );
                    }
                }
            }
            else{
                throw new ServiceException(ex.getMessage());
            }                
        }

        return "OK";

    }

    @Override
    public String consultar(Material material) throws ServiceException {
        
        String mensagem = "";

        if (!material.getClass().equals(Eletrodomestico.class)) 
            throw new ServiceException("Tipo de usuário inválido!");

        try {
            validacaoMaterial.validacao(material);
        } catch(ServiceException ex){
            if(ex.getMessage().equals("Usuario existente!") ){
                Eletrodomestico eletrodomesticoResultado;
                eletrodomesticoResultado = (Eletrodomestico) materialDAO.consultar(material.getId());
                if(eletrodomesticoResultado != null)
                   return eletrodomesticoResultado.toString();
                else
                    mensagem = "Operacao invalida!";
            }
            else
                mensagem = "Eletrodomestico não existe!";
        }
        throw new ServiceException(mensagem);

    }

    @Override
    public List<String> consultarTodos() throws ServiceException {
            List<Eletrodomestico> eletrodomesticos = new ArrayList<>();
            List<Material> materials = materialDAO.consultarTodos();
            
            if(materials.isEmpty()) 
                throw new ServiceException("Não há eletrodomesticos!");
            
            for(Material material: materials){
                eletrodomesticos.add((Eletrodomestico) material);
            }
            
            List<String> retornos = new ArrayList<>();
            
            for(Eletrodomestico eletrodomestico: eletrodomesticos){
                retornos.add(eletrodomestico.toString());
            }
            
            return retornos;
    
    }

    @Override
    public List<String> consultaEspecifica(List<String> params, List<String> keys) throws ServiceException {
        
        boolean entrou = false;
        List<String> eletrodomesticosDados = this.consultarTodos();
        List<String> eletrodomesticosResultado = new ArrayList<>();
        List<String> teste = new ArrayList<>();


        for (Iterator<String> itParam = params.iterator(), itKey = keys.iterator(); 
                itParam.hasNext() && itKey.hasNext();) {
            String param = itParam.next();
            String key = itKey.next();
            for(String eletrodomestico: eletrodomesticosDados){
                String atributos[] = eletrodomestico.split(".");
                for(String atributoValor : atributos){
                    if(atributoValor.matches(param)){
                        if(atributoValor.matches(key)){
                            teste.add(eletrodomestico);
                            break;
                        }
                    }
                }    
            }

            if(!entrou && eletrodomesticosResultado.isEmpty()){
                entrou = true;
                eletrodomesticosResultado.addAll(teste);
            }
            else 
                eletrodomesticosResultado.retainAll(teste);

            teste.clear();
        }

        return eletrodomesticosResultado;

    }

    @Override
    public List<String> ranquear(IRankingMaterialStrategy rankingMaterial) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String validacao(Material material) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
