package master.cours.gcommerce.contollers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import master.cours.gcommerce.entities.Produit;
import master.cours.gcommerce.services.ProduitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("ventes")
public class ProduitController {
    final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @Operation(description = "ajouter produit")
    @PostMapping("produits")
    public Produit addProduit(@Parameter(description = "Introduire un nouveau produit") @RequestBody Produit produit){
        return this.produitService.addProduit(produit);
    }
    @GetMapping("produits")
    public List<Produit> getAllProduits(){
        return this.produitService.getAllProduits();
    }

    @GetMapping("produits/{id}")
    public Produit getProduitById(@PathVariable UUID id){

        return produitService.getProduitById(id);
    }

    @DeleteMapping("produits/{id}")
    public boolean deleteProduit(@PathVariable UUID id){
        return produitService.deleteProduitById(id);
    }

}
