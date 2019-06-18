package service.raqueamento;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/// CLASSES PRÓPRIAS
import model.Material;

/**
 * Classe de ranqueamento por meio da data de entrada de material
 * @see IRankingMaterialStrategy
 */
public class RankingDataEntrada implements IRankingMaterialStrategy {

    public class MaterialDataEntrada implements Comparator<Material>{
        @Override
        public int compare( Material material_1, Material material_2 ){
            return material_1.getDataEntrada().compareTo(material_2.getDataEntrada());
        }
    }

    /// MÉTODOS **********************************************************************************
  	
    @Override
    public List<Material> ranquear(List<Material> materiais) {
        Collections.sort( materiais, new MaterialDataEntrada() );
        return materiais;
    }

}
