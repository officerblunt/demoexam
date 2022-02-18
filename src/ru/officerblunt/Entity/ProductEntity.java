package ru.officerblunt.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductEntity {
    private int ID;
    private String title;
    private String productType;
    private int articleNumber;
    private int productionPersonCount;
    private int productionWorkshopNumber;
    private double minCostForAgent;

    public ProductEntity(String title, String productType, int articleNumber, int productionPersonCount, int productionWorkshopNumber, double minCostForAgent) {
        this.title = title;
        this.productType = productType;
        this.articleNumber = articleNumber;
        this.productionPersonCount = productionPersonCount;
        this.productionWorkshopNumber = productionWorkshopNumber;
        this.minCostForAgent = minCostForAgent;
    }
}
