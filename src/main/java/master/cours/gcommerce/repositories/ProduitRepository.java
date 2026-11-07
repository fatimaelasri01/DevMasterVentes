package master.cours.gcommerce.repositories;

import master.cours.gcommerce.dtos.ProduitDTO;
import master.cours.gcommerce.entities.CategorieStats;
import master.cours.gcommerce.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProduitRepository extends JpaRepository<Produit, UUID>, JpaSpecificationExecutor<Produit> {
    // jpql travaille sur les objets que nous avons par exemple ici nous avons utiliserl entite Produit
    @Query("select new master.cours.gcommerce.entities.CategorieStats(p.categorie.nom, count(p), avg(p.prix)) from Produit p group by p.categorie.nom")
    List<CategorieStats> compterProduitsParCategorie();

    List<Produit> findByCategorieNom(String nomcat);
}
