package master.cours.gcommerce.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import master.cours.gcommerce.entities.Produit;
import org.springframework.data.jpa.domain.Specification;

// les specifications sont toujours des fonctions statiques
public class ProduitSpecification {
    public static Specification<Produit> designationContient(String nom){
        //root permet d acceder a une produit a partir d une specification
        // query c est select * from produit
        // cb c est le where
        return ((root, query, cb) ->
                cb.like(root.get("designation"), "%"+nom+"%")
                );
    }

    public static Specification<Produit> prixInferieur(Double prix){
        return (root,query,cb)->
                cb.lessThanOrEqualTo(root.get("prix"),prix);
    }

    public static Specification<Produit> dansCategorie(String nom){
        return (root,query,cb)->
                cb.equal(root.get("categorie").get("nom"),nom);
    }
}
