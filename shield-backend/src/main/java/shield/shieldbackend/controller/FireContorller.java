package shield.shieldbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import shield.shieldbackend.dto.FireDto;
import shield.shieldbackend.service.FireService;
import shield.shieldbackend.service.EC2Service;

@RestController
@RequiredArgsConstructor
public class FireContorller {
    private final FireService fireService;
    private final EC2Service EC2Service;

    // 특정 화재 정보 페이지
    @GetMapping("api/fire-Info/{camNum}")
    public FireDto getFireData(@PathVariable String camNum) {
        String cam1FileName = EC2Service.getLatestImageFileNameCam1();
        String cam2FileName = EC2Service.getLatestImageFileNameCam2();

        if (cam1FileName != null) {
            fireService.processCam1File(cam1FileName);
        }

        if (cam2FileName != null) {
            fireService.processCam2File(cam2FileName);
        }

        return fireService.getFireData(camNum);
    }

    // 지도 페이지 (페이지 로드했을 때 화재 정보 저장 위함)
    @GetMapping("api/fire-Info")
    public ResponseEntity<String> saveFireData() {
        String cam1FileName = EC2Service.getLatestImageFileNameCam1();
        String cam2FileName = EC2Service.getLatestImageFileNameCam2();

        fireService.processCam1File(cam1FileName);
        fireService.processCam2File(cam2FileName);

        return ResponseEntity.ok("Success");
    }
}
