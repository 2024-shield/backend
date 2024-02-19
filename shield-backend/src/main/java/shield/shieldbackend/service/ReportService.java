package shield.shieldbackend.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shield.shieldbackend.domain.Member;
import shield.shieldbackend.dto.ReportDto;
import shield.shieldbackend.dto.ReportFixedDto;
import shield.shieldbackend.entity.Fire;
import shield.shieldbackend.entity.Report;
import shield.shieldbackend.repository.FireRepository;
import shield.shieldbackend.repository.MemberRepository;
import shield.shieldbackend.repository.ReportRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final FireRepository fireRepository;
    private final MemberRepository memberRepository;

    public ReportFixedDto getFixedData(Long fireId, Long memberId) {
        Fire fire = fireRepository.findById(fireId)
                .orElseThrow(()-> new EntityNotFoundException("Fire not found"));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new EntityNotFoundException("Member not found"));
        return new ReportFixedDto(fire.getFireDate(), fire.getFireTime(), fire.getFirePlace(),
                member.getName(), member.getDepartment());
    }

    @Transactional
    public void createReport(ReportDto reportDto, Long fireId, Long memberId) {
        Report report = new Report();
        report.setCause(reportDto.getCause());
        report.setDeathNum(reportDto.getDeathNum());
        report.setInjuryNum(reportDto.getInjuryNum());
        report.setTheDead(reportDto.getTheDead());
        report.setTheInjured(reportDto.getTheInjured());
        report.setMoney(reportDto.getMoney());
        report.setWorkerNum(reportDto.getWorkerNum());
        report.setEquipNum(reportDto.getEquipNum());
        report.setAction(reportDto.getAction());

        Fire fire = fireRepository.findById(fireId)
                .orElseThrow(()-> new EntityNotFoundException("Fire not found"));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new EntityNotFoundException("Member not found"));

        report.setFire(fire);
        report.setMember(member);

        reportRepository.save(report);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    // 보고서 수정
    public void updateReport(Long reportId, ReportDto updateReportDto) {
        // 보고서 엔티티를 DB에서 찾음
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Report not found"));

        // 수정된 보고서의 데이터로 엔티티 업데이트
        report.setCause(updateReportDto.getCause());
        report.setDeathNum(updateReportDto.getDeathNum());
        report.setInjuryNum(updateReportDto.getInjuryNum());
        report.setTheDead(updateReportDto.getTheDead());
        report.setTheInjured(updateReportDto.getTheInjured());
        report.setMoney(updateReportDto.getMoney());
        report.setWorkerNum(updateReportDto.getWorkerNum());
        report.setEquipNum(updateReportDto.getEquipNum());
        report.setAction(updateReportDto.getAction());

        reportRepository.save(report);
    }
}
