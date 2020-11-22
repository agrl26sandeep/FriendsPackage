package com.solution.parser;

import com.solution.domain.PackageItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class is used to parse the String input data with different items.
 * @author Sandeep Agrawal
 */
public class FriendPackageRequestParser implements IDataParser<List<String>, Map<Integer, List<PackageItem>>> {

    private Map<Integer, List<PackageItem>> packagItemMap;

    /**
     *  Method is to parse List<String> to Map<Integer, List<PackageItem>>
     * @param source
     * @return
     */
    @Override
    public Map<Integer, List<PackageItem>> parse(List<String> source) {
        if(null==source)
            return null;

        packagItemMap = new LinkedHashMap<>();
        for(int ik=0; ik < source.size(); ik++) {
            List<PackageItem> packageItems = new ArrayList<PackageItem>();
            String[] textLine = source.get(ik).split(" : ");
            //If each input line has no max weight available
            if(null == textLine[0] || textLine[0].isEmpty())
                textLine[0] = "0";
            double maxWeight = Double.parseDouble(textLine[0]);
            //apply constraint if maxWieght is smaller than and equal 100 then only process line items
            packageByMaxWeightAndCost(textLine[1].trim().split(" "), packageItems, maxWeight);
            packagItemMap.put(Integer.parseInt(textLine[0]), packageItems);
        }
        return packagItemMap;
    }

    /**
     *  Prepare List of packaged item based on allowed maximum weight and cost per item
     * @param txtItems
     */
    private void packageByMaxWeightAndCost(String[] txtItems, List<PackageItem> lineItems, double maxWeight) {
        for(int itr=0; itr < txtItems.length; itr++) {
            String[] items = (txtItems[itr].substring(1, txtItems[itr].length() - 1)).split(",");
            double itemWeight = Double.parseDouble(items[1]);
            double itemCost = 0;
            try {
                if(null == items[2] || items[2].isEmpty())
                    continue;

                itemCost = Double.parseDouble(items[2].substring(1, items[2].length()));
            } catch (NumberFormatException nfe){
                try {
                    itemCost = Double.parseDouble(items[2].replace("â‚¬", ""));
                } catch (NumberFormatException ex){
                    continue;
                }
            }
            //The maximum weight of an item should be <= 100
            //The maximum cost of an item should be <= €100
            if(itemWeight <= maxWeight && itemCost <= 100 && itemWeight <= 100)
                lineItems.add(new PackageItem(Integer.parseInt(items[0]), itemCost, itemWeight));
        }
    }

    /*public static void main(String[] args) throws FileReaderException {
        new FriendPackageRequestParser().parse(
                (List<String>)FileReaderFactory.getInstance().readFile(ReaderAction.READ_TXT, "input/sampleInput.txt"));
    }*/

}
