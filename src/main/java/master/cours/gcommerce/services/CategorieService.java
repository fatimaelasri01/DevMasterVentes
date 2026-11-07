package master.cours.gcommerce.services;

import master.cours.gcommerce.dtos.CategorieDTO;
import master.cours.gcommerce.dtos.ProduitDTO;
import master.cours.gcommerce.entities.Categorie;
import master.cours.gcommerce.entities.Produit;
import master.cours.gcommerce.repositories.CategorieRepository;
import master.cours.gcommerce.repositories.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    final CategorieRepository categorieRepository;
    final ProduitRepository produitRepository;

    public CategorieService(CategorieRepository categorieRepository, ProduitRepository produitRepository) {
        this.categorieRepository = categorieRepository;
        this.produitRepository = produitRepository;
    }

    public List<CategorieDTO> getAllCategories() {
        return categorieRepository.findAll().stream().map(Categorie::toDTO).toList();
    }

    public CategorieDTO addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie).toDTO();
    }

    public ProduitDTO addProduit(String nomcat, Produit produit) {
        Categorie categorie = categorieRepository.findCategorieByNom(nomcat);
        //categorie.getProduits().add(produit);
        //categorieRepository.save(categorie);
        produit.setCategorie(categorie);
        return produitRepository.save(produit).toDTO();
    }

    public List<ProduitDTO> getAllProduits(String nomcat) {
        //return categorieRepository.findCategorieByNom(nomcat).getProduits().stream().map(Produit::toDTO).toList();  // produit->produit.toDTO()
        // stream : transforme une liste a une flux pour luis appliquer une transformation
        // map va appliquer la transformation a chaque element de stream
        return produitRepository.findByCategorieNom(nomcat).stream().map(Produit::toDTO).toList();
        // apres by on a l attribut de produit
        // si on fait categorienom il va considerer que ce nom est un attribut de produit mais avec N il va considerer que categorie est un attribut de produit et il va chercher dans categorie le nom
    }

    public boolean deleteCategorieById(Long id){
        categorieRepository.deleteById(id);
        return !categorieRepository.existsById(id);
    }
}
