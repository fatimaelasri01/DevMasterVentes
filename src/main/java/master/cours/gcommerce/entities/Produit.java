package master.cours.gcommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String designation;
    private double prix;
    private int quantite;
}
