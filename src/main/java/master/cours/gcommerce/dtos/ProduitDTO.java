package master.cours.gcommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// dto : design patterns " data transform object?? "

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProduitDTO {
    private UUID id;
    private String designation;
    private double prix;
    private int quantite;
    private CategorieDTO categorie; //pour ne pas renvoyer toute la classe categorie car si je revois categorie on va renvoyer touts les produit de categorie donc en va entrer dans la boucle avec dto on aura plus besion de @ignorejson
}
