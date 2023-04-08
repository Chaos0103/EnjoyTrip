package hotplace.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class HotPlaceListDto {

    private Long id;
    private String name;
    private String desc;
    private int hit;

    private String nickname;
    private String createdDate;

    @Builder
    public HotPlaceListDto(Long id, String name, String desc, int hit, String nickname, String createdDate) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.hit = hit;
        this.nickname = nickname;
        this.createdDate = createdDate;
    }
}
