package id.maskipli.com.movies.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hidayat on 11/17/16.
 */

public class SectionDataModel {

    private String headerTitle;
    private List<SingleItemModel> allItemsInSection;


    public SectionDataModel() {
    }
    public SectionDataModel(String headerTitle, ArrayList<SingleItemModel> allItemsInSection) {
        this.headerTitle = headerTitle;
        this.allItemsInSection = allItemsInSection;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public List<SingleItemModel> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(List<SingleItemModel> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }
}
