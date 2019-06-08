package model;

import javax.persistence.Embeddable;

import exception.ServiceException;
import java.io.Serializable;

@Embeddable
public class Data implements Serializable {
    
    /// ATRIBUTOS ********************************************************************************
    
    private String dia;
    private String mes;
    private String ano;
    private String data; /// USADO APENAS INTERNAMENTE
    
    /// CONSTRUTOR *******************************************************************************
    
    /**
     * Construtor da classe Data
     * @param data  Data em formato de string a ser armazenada
     * @throws ServiceException 
     */
    Data(String data) throws ServiceException{

        String diaTemporario = data.split("/")[0];
        String mesTemporario = data.split("/")[1];
        String anoTemporario = data.split("/")[2];

        if( diaTemporario.length() != 2
            ||    Integer.parseInt( diaTemporario ) < 1 
            || ( Integer.parseInt( diaTemporario ) > 31  && (  Integer.parseInt( mesTemporario ) == 1  || 
                                                            Integer.parseInt( mesTemporario ) == 3  || Integer.parseInt( mesTemporario ) == 5 || 
                                                            Integer.parseInt( mesTemporario ) == 7  || Integer.parseInt( mesTemporario ) == 8 ||
                                                            Integer.parseInt( mesTemporario ) == 10 || Integer.parseInt( mesTemporario ) == 12  ) 

            || ( Integer.parseInt( diaTemporario ) > 30  && (  Integer.parseInt( mesTemporario ) == 4  || Integer.parseInt( mesTemporario ) == 6 || 
                                                            Integer.parseInt( mesTemporario ) == 9  || Integer.parseInt( mesTemporario ) == 11 ) )

            || ( Integer.parseInt( diaTemporario ) > 28  && (  Integer.parseInt( mesTemporario ) == 2 )    )

            )  
           )
            throw new ServiceException("Dia invalido");

        if( mesTemporario.length() != 2 || 
            Integer.parseInt(mesTemporario) < 1 || 
            Integer.parseInt(mesTemporario) > 12 )
            throw new ServiceException("Mes invalido");

        if( anoTemporario.length() != 4  ||
            Integer.parseInt(anoTemporario) < 1920 ||
            Integer.parseInt(anoTemporario) > 2050 )
            throw new ServiceException("Ano invalido");

        this.dia = diaTemporario;
        this.mes = mesTemporario;
        this.ano = anoTemporario;
        this.data = data;
    }
    
    /// GETTERS E SETTERS ************************************************************************
    
    public String getDia() {
        return this.dia;
    }

    public String getMes() {
        return this.mes;
    }

    public String getAno() {
        return this.ano;
    }
    
    /// MÉTODOS **********************************************************************************
    
    public void set(int dia, int mes, int ano) {
    	this.dia = String.valueOf(dia);
    	this.mes = String.valueOf(mes);
    	this.ano = String.valueOf(ano);
    	
    }
    /**
     * Envia a string que cont�m a data
     * @return  A data em formato de string
     */
    @Override
    public String toString(){
        return this.data;
    }

    public int compareTo(Data dataEntrada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
