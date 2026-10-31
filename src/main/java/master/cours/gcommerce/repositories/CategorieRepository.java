package master.cours.gcommerce.repositories;

import master.cours.gcommerce.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    Categorie findCategorieByNom(String nomcat);
}
