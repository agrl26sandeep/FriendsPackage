package com.solution.processor;

import com.solution.domain.PackageItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class is used to process and generate the valid friends package item response.
 * @author Sandeep Agrawal
 */
public class FriendPackageProcessor implements IProcessor<Map<String, List<PackageItem>>, Map<Integer, List<PackageItem>>> {
    private Map<String, List<PackageItem>> finalPackageMap;
    private int superIndex = 0;
    private double maxItemWeight = 0;
    private double maxItemCost = 0;
    private int itemIndex = 0;
    private double packageWeight = 0;
    private List<Integer> combinationIndexList;
    private String validItemIndex;

    private void setSuperIndex(int superIndex) {
        this.superIndex = superIndex;
    }

    private void setPackageWeight(double packageWeight) {
        this.packageWeight = packageWeight;
    }

    /**
     * Process list of items against maximum weight, compute and finalise the friends package items/value.
     * @param packagedMap
     * @return
     */
    @Override
    public Map<String, List<PackageItem>> process(Map<Integer, List<PackageItem>> packagedMap) {
        if(null != packagedMap) {
            finalPackageMap = new LinkedHashMap<>();
            packagedMap.entrySet().stream().forEach(entry-> {
                if(entry.getValue().isEmpty()) {
                    System.out.println("-");
                    finalPackageMap.put("-", null);
                } else {
                    preparePackageItems(entry.getValue(), compute(entry.getValue(), entry.getKey()));
                }
            });
        }
        return finalPackageMap;
    }

    /**
     *  This method used to collect and maintain the final computed package items.
     *  Prepare the list of package items based on computed result.
     *  Prepare the final package map for the response
     * @param pakItemList
     * @param computedResult
     */
    private void preparePackageItems(List<PackageItem> pakItemList, String computedResult) {
        List<PackageItem> pakagedList = new ArrayList<>();
        String[] finalCombinedItem = computedResult.split(",");
        String[] itemNums = new String[finalCombinedItem.length - 2];
        for (int i = 0; i < finalCombinedItem.length - 2; i++) {
            itemNums[i] = pakItemList.get(Integer.parseInt(finalCombinedItem[i])).getItemNumber().toString();
            pakagedList.add(pakItemList.get(Integer.parseInt(finalCombinedItem[i])));
        }
        System.out.println(String.join(",", itemNums));
        finalPackageMap.put(String.join(",", itemNums), pakagedList);;
    }

    /**
     *  Compute and identify the maximum weight and cost for package
     * @param pakItemList
     * @param key
     * @return String
     */
    private String compute(List<PackageItem> pakItemList, int key) {
        double maxCost = 0;
        double maxWeight = 0;
        String result = "";
        setPackageWeight(key);
        for (int itr = 1; itr <= pakItemList.size(); itr++) {
            setSuperIndex(itr);
            String combineItem = ComputePrimaryCombination(pakItemList);
            //System.out.println(combineItem);
            String[] combineItemArr = combineItem.split(",");
            double cost = Double.parseDouble(combineItemArr[combineItemArr.length - 2]);
            double weight = Double.parseDouble(combineItemArr[combineItemArr.length - 1]);
            if (cost == maxCost) {
                if (weight < maxWeight) {
                    maxCost = cost;
                    maxWeight = weight;
                    result = combineItem;
                }
            } else if (cost > maxCost) {
                maxCost = cost;
                maxWeight = weight;
                result = combineItem;
            }
        }
        return result;
    }

    /**
     * Compute the item index for those items which are satisfied to adding in package.
     * @param pakItemList
     * @return
     */
    private String ComputePrimaryCombination(List<PackageItem> pakItemList) {
        this.validItemIndex = "";
        this.itemIndex = 0;
        this.maxItemCost = 0;
        this.maxItemWeight = 0;
        getIndexPamutation(pakItemList);
        for (int first=0; first <= this.combinationIndexList.size() - this.superIndex; first += this.superIndex) {
            computeSubCombination(pakItemList, first);
        }

        for (int k = itemIndex; k < this.superIndex + this.itemIndex && this.validItemIndex.split(",").length != 15; k++) {
            this.validItemIndex += this.combinationIndexList.get(k) + ",";
        }
        return this.validItemIndex + this.maxItemCost + "," + this.maxItemWeight;
    }
    /**
     * Compute the maxItemWeight and maxItemCost for all subSet items.
     * @param pakItemList
     * @param firstLoopIndex
     */
    private void computeSubCombination(List<PackageItem> pakItemList, int firstLoopIndex) {
        double itemWeight = 0;
        double itemCost = 0;
        for (int second=0; second < this.superIndex; second++) {
            itemCost += pakItemList.get(this.combinationIndexList.get(firstLoopIndex + second)).getCost();
            itemWeight += pakItemList.get(this.combinationIndexList.get(firstLoopIndex + second)).getWeight();
        }
        //Maximum number items in a package must be 15
        if(this.validItemIndex.split(",").length == 15) {
            return;
        }
        //The maximum weight that a package can hold must be <= 100
        if (itemWeight <= this.packageWeight && itemWeight <= 100) {
            if ((itemCost > this.maxItemCost) || ((itemCost == this.maxItemCost) && (itemWeight <= this.maxItemWeight) )) {
                this.maxItemWeight = itemWeight;
                this.maxItemCost = itemCost;
                this.itemIndex = firstLoopIndex;
            }
        }
    }

    /**
     * Generate permutation & combination of item index.
     * @param pakItemList
     * @return
     */
    private void getIndexPamutation(List<PackageItem> pakItemList){
        int[] data = new int[this.superIndex];
        int[] packageArray = new int[pakItemList.size()];
        this.combinationIndexList = new ArrayList<Integer>();
        for (int i = 0; i < pakItemList.size(); i++) {
            packageArray[i] = i;
        }
        getCombinationIndexList(packageArray, data, 0, 0);
    }

    /**
     * Applied recursive approach to consider all subsets of items.
     * @param packageArray
     * @param data
     * @param startIndex
     * @param dataIndex
     */
    private void getCombinationIndexList(int[] packageArray, int[] data, int startIndex, int dataIndex) {
        if (dataIndex == data.length) {
            for (int j = 0; j < data.length; j++) {
                this.combinationIndexList.add(data[j]);
            }
            return;
        }
        for (int i = startIndex; i < packageArray.length && packageArray.length - i >= data.length - dataIndex; i++) {
            data[dataIndex] = packageArray[i];
            getCombinationIndexList(packageArray, data, i + 1, dataIndex + 1);
        }
    }
}
