package shield.shieldbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shield.shieldbackend.dto.ReportDto;
import shield.shieldbackend.dto.ReportFixedDto;
import shield.shieldbackend.entity.Report;
import shield.shieldbackend.service.ReportService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    // 보고서 작성 페이지에서 보여주는 것들
    @GetMapping("api/reports/create/{fireId}/{memberId}")
    public ReportFixedDto getFixedData(@PathVariable Long fireId, @PathVariable Long memberId) {
        return reportService.getFixedData(fireId, memberId);
    }

    // 보고서 작성
    @PostMapping("api/reports/create/{fireId}/{memberId}")
    public ResponseEntity<String> createReport(@RequestBody ReportDto reportDto,
                                               @PathVariable Long fireId, @PathVariable Long memberId) {
        try {
            reportDto.setMemberId(memberId);
            reportDto.setFireId(fireId);

            reportService.createReport(reportDto, fireId, memberId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Report created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create report.");
        }
    }

    // 보고서 목록
    @GetMapping("/api/reports")
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reportList = reportService.getAllReports();
        return ResponseEntity.ok(reportList);
    }

    // 보고서 수정
    @PutMapping("/api/reports/{reportId}")
    public ResponseEntity<String> updateReport(@PathVariable Long reportId, @RequestBody ReportDto updateReportDto) {
        try {
            reportService.updateReport(reportId,updateReportDto);
            return ResponseEntity.ok("Report updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to updated report.");
        }
    }
}
