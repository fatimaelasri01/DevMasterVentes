package master.cours.gcommerce.repositories;

import master.cours.gcommerce.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProduitRepository extends JpaRepository<Produit, UUID> {
}
