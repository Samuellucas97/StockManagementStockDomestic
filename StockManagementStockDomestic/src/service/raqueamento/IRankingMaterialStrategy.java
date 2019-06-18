package service.raqueamento;

import java.util.List;

/// CLASSE PRÓPRIA
import model.Material;

/**
 * Interface de algoritmos de ranqueamento de material
 * @see Material
 * @see List
 */
public interface IRankingMaterialStrategy {

    /**
     * Ordena lista de materiais
     * @param materiais Lista de materiais a serem ordenados
     * @return  Lista de materiais reordenada 
     */
    public List<Material> ranquear(List<Material> materiais);

}
