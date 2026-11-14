package master.cours.gcommerce.contollers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import master.cours.gcommerce.dtos.ProduitDTO;
import master.cours.gcommerce.entities.CategorieStats;
import master.cours.gcommerce.entities.Produit;
import master.cours.gcommerce.repositories.ProduitRepository;
import master.cours.gcommerce.services.ProduitService;
import master.cours.gcommerce.specifications.ProduitSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("ventes")
public class ProduitController {
    final ProduitService produitService;
    final ProduitRepository produitRepository;

    public ProduitController(ProduitService produitService, ProduitRepository produitRepository) {
        this.produitService = produitService;
        this.produitRepository = produitRepository;
    }

    /*
    @Operation(description = "ajouter produit")
    @PostMapping("produits")
    public Produit addProduit(@Parameter(description = "Introduire un nouveau produit") @RequestBody Produit produit){
        return this.produitService.addProduit(produit);
    }
    */

    @GetMapping("produits")
    // avec page=2 il va afficher la deuxieme page
    public List<Produit> getAllProduits(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        return this.produitService.getAllProduits(page, size);
    }

    @GetMapping("produits/{id}")
    public Produit getProduitById(@PathVariable UUID id){

        return produitService.getProduitById(id);
    }

    @DeleteMapping("produits/{id}")
    public boolean deleteProduit(@PathVariable UUID id){
        return produitService.deleteProduitById(id);
    }

    @GetMapping("/stats")
    // avec objet il va pas retourner un vrai json juste une liste de liste
    public List<CategorieStats> stats(){
        return produitService.compterProduitParCategorie();
    }

    @GetMapping("search")
    public List<ProduitDTO> rechercher(
            @RequestParam(required = false) String designation,
            @RequestParam(required = false) Double prixMax,
            @RequestParam(required = false) String categorieNom
    ){
        //
        Specification<Produit> spec=Specification.unrestricted();
        if (designation!=null && !designation.isBlank()){
            spec=spec.and(ProduitSpecification.designationContient(designation));
        }
        if (prixMax!=null){
            spec=spec.and(ProduitSpecification.prixInferieur(prixMax));
        }
        if (categorieNom!=null){
            spec=spec.and(ProduitSpecification.dansCategorie(categorieNom));
        }
        return produitRepository.findAll(spec).stream().map(Produit::toDTO).toList();
    }
}
