package master.cours.gcommerce.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import master.cours.gcommerce.dtos.CategorieDTO;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    String nom;

    // fetchtype lazy findbyid ou all il revoit juste id et nom et list vide de produit liste de categories sont produits, saut si on appel categorie.getproduit() avec getters cette regle est applique ors web donc dans notre cas puisqu on a controller on vois pas de difference grace a Jackson utiliser pour transformer objet en json et qui utiliser les getters
    // encascade si on supprime categorie les produits seront supprimer
    // mappedBy categorie est mapper dans produit par categorie
    // @JsonIgnore la recursion cyclique chaqu une appele l autre est ce que quand on charge categorie ce n est pas obligatoire d afficher liste de produit donc on ajoute @JsonIgnore mais ce n est pas la bon maniere, on pose la meme question dans produit et si obligatoire de voir la categorie donc on l ajoute pas
    // on aura plus besion car nous avons utiliser dto
    @OneToMany(mappedBy = "categorie")
    List<Produit> produits;

    public CategorieDTO toDTO(){
        return new CategorieDTO(id, nom);
    }
}
