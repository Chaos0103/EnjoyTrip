package attraction.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AttractionDto {

    private String title;
    private String addr1;
    private String zipcode;
    private String firstImage;
    private Double latitude;
    private Double longitude;

    @Builder
    public AttractionDto(String title, String addr1, String zipcode, String firstImage, Double latitude, Double longitude) {
        this.title = title;
        this.addr1 = addr1;
        this.zipcode = zipcode;
        this.firstImage = firstImage;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
