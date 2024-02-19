package shield.shieldbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportFixedDto {
    private String fireDate; // 화재 발생 날짜
    private String fireTime; // 화재 발생 시각
    private String firePlace; // 화재 발생 장소

    private String name; // 작성자 이름
    private String department; // 관할 소방서

    public ReportFixedDto(String fireDate, String fireTime, String firePlace,
                          String name, String department) {
        this.fireDate = fireDate;
        this.fireTime = fireTime;
        this.firePlace = firePlace;
        this.name = name;
        this.department = department;
    }
}
