package master.cours.gcommerce.contollers;


import master.cours.gcommerce.dtos.CategorieDTO;
import master.cours.gcommerce.dtos.ProduitDTO;
import master.cours.gcommerce.entities.Categorie;
import master.cours.gcommerce.entities.Produit;
import master.cours.gcommerce.services.CategorieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ventes/categories")
public class CategorieController {

    final CategorieService categorieService;


    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    public List<CategorieDTO> getAllCategories() {
        return categorieService.getAllCategories();
    }

    @PostMapping
    public CategorieDTO addCategorie(@RequestBody Categorie categorie){
        return categorieService.addCategorie(categorie);
    }

    // si mieux de faires /ventes/categories/nomcategorie/produits pour ajouter un produit a une categorie pour suivres la convontien, c est produit qui appartient a une categorie
    @PostMapping("{nomcat}/produits")
    public ProduitDTO addProduit(@PathVariable String nomcat, @RequestBody Produit produit){
        return categorieService.addProduit(nomcat, produit);
    }

    @GetMapping("{nomcat}/produits")
    public List<ProduitDTO> getAllProduits(@PathVariable String nomcat){
        return categorieService.getAllProduits(nomcat);
    }

    @DeleteMapping("{id}")
    public boolean deleteCategorieById(@PathVariable Long id){
        return categorieService.deleteCategorieById(id);
    }

    // dto des classes qui permet de reccuperer juste quelque champs ne jamais renvoyer l entite
}
