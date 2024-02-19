package shield.shieldbackend.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shield.shieldbackend.dto.FireDto;
import shield.shieldbackend.entity.Fire;
import shield.shieldbackend.repository.FireRepository;

@Service
@RequiredArgsConstructor
public class FireService {
    private final FireRepository fireRepository;

    public FireDto getFireData(Long fireId) {
        Fire fire = fireRepository.findById(fireId)
                .orElseThrow(()-> new EntityNotFoundException("Fire not found"));
        // trut == "F"인 경우
        if (!"T".equals(fire.getTruth())) {
            return null;
        }
        // truth == "T"인 경우
        return new FireDto(fire.getFireTime(), fire.getFirePlace());
    }
}
