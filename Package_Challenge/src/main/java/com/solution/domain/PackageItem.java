package com.solution.domain;

/**
 * Class holds the package items related attributes.
 * @author Sandeep Agrawal
 */
public class PackageItem {
    private int itemNumber;
    private double cost;
    private double weight;

    public PackageItem(int itemNumber, double cost, double weight) {
        this.itemNumber = itemNumber;
        this.cost = cost;
        this.weight = weight;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PackageItem{" +
                "itemNumber=" + itemNumber +
                ", cost=" + cost +
                ", weight=" + weight +
                '}';
    }
}
