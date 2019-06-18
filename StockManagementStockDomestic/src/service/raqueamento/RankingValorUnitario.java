
package service.raqueamento;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/// CLASSE PRÓPRIA
import model.Material;

/**
 * Classe de ranqueamento por meio do valor unitário de material
 * @see IRankingMaterialStrategy
 */
public class RankingValorUnitario implements IRankingMaterialStrategy {

    public class MaterialValorUnitario implements Comparator<Material>{
        @Override
        public int compare( Material material_1, Material material_2 ){
            if(  material_1.getValorUnitario() < material_2.getValorUnitario() ){
                return -1;
            }	
            else if(  material_1.getValorUnitario() > material_2.getValorUnitario() ){
                return 1;
            }
            else{
                return 0;
            }	
        }
    }

    /// MÉTODOS **********************************************************************************
	
    @Override
    public List<Material> ranquear(List<Material> materiais) {
        Collections.sort( materiais, new MaterialValorUnitario() );
        return materiais;
    }

}
