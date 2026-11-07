package master.cours.gcommerce.services;

import master.cours.gcommerce.entities.CategorieStats;
import master.cours.gcommerce.entities.Produit;
import master.cours.gcommerce.repositories.ProduitRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProduitService {
    final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    /*
    public Produit addProduit(Produit produit) {
        return this.produitRepository.save(produit);
    }*/

    public List<Produit> getAllProduits(int page, int size) {
        // objet pagination pour definir le nombre des pages et le size
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("designation").ascending());
        return produitRepository.findAll(pageRequest).getContent();
        // on ajoute getcontent car on renvoie une list de produit et non pa une page
    }

    public Produit getProduitById(UUID id) {
        return produitRepository.findById(id).orElse(null); // optional ca veut dire si on ne revoit rien n a pas de probleme on ajoute orElse(null) si il retourn rien car on retourn produit et non optionnal
    }

    public boolean deleteProduitById(UUID id) {
        produitRepository.deleteById(id);
        return !produitRepository.existsById(id); //pour verifier que le produit est bien supprimer
    }

    public List<CategorieStats> compterProduitParCategorie() {
        return produitRepository.compterProduitsParCategorie();
    }
}
