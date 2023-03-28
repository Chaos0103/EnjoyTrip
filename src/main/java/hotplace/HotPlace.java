package hotplace;

import attraction.AttractionInfo;
import lombok.Builder;
import lombok.Getter;
import member.Member;

import java.time.LocalDateTime;

@Getter
public class HotPlace {

    private Long id;
    private String name;
    private String desc;
    private String visitedDate;
    private UploadFile uploadFile;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    private Integer contentTypeId;
    private Member member;
    private AttractionInfo attractionInfo;

    @Builder
    public HotPlace(Long id, String name, String desc, String visitedDate, UploadFile uploadFile, LocalDateTime createdDate, LocalDateTime lastModifiedDate, Integer contentTypeId, Member member, AttractionInfo attractionInfo) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.visitedDate = visitedDate;
        this.uploadFile = uploadFile;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.contentTypeId = contentTypeId;
        this.member = member;
        this.attractionInfo = attractionInfo;
    }
}
