package hotplace.dto;

import lombok.Data;

@Data
public class HotPlaceSearch {

    private String name;
    private int sortCondition;

    public HotPlaceSearch(String name, int sortCondition) {
        this.name = name;
        this.sortCondition = sortCondition;
    }
}
