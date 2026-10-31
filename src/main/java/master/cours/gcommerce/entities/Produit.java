package master.cours.gcommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import master.cours.gcommerce.dtos.ProduitDTO;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String designation;
    private double prix;
    private int quantite;

    //pour la jointure on lit de haut au bas par exemple ici on a many produit pour une categorie
    //cascade si on supprime parent les enfants sont supprimer ,  fetch lorsque je recupere un produit je recupere ca valeur acec lazy quand je recupere l objet parent je recupere pas ses enfants
    @JsonIgnore
    @ManyToOne
    Categorie categorie;

    public ProduitDTO toDTO(){
       return new ProduitDTO(id, designation, prix, quantite, categorie.toDTO());
    }
}
