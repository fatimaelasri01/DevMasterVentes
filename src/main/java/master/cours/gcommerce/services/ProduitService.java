package master.cours.gcommerce.services;

import master.cours.gcommerce.entities.Produit;
import master.cours.gcommerce.repositories.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {
    final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }


    public Produit addProduit(Produit produit) {
        return this.produitRepository.save(produit);
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }
}
