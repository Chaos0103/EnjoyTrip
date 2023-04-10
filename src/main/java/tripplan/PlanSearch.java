package tripplan;

import lombok.Builder;
import lombok.Data;
import member.Member;

import java.time.LocalDateTime;

@Data
public class PlanSearch {
    private Member member;
    private String title;
    private LocalDateTime createdDate;

    @Builder
    public PlanSearch(Member member, String title, LocalDateTime createdDate) {
        this.member = member;
        this.title = title;
        this.createdDate = createdDate;
    }
}
