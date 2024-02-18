package shield.shieldbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import shield.shieldbackend.dto.FireDto;
import shield.shieldbackend.service.FireService;

@RestController
@RequiredArgsConstructor
public class FireContorller {
    private final FireService fireService;

    // 화재 정보 페이지
    @GetMapping("api/fire-Info/{fireId}")
    public FireDto getFireData(@PathVariable Long fireId) {
        return fireService.getFireData(fireId);
    }
}
