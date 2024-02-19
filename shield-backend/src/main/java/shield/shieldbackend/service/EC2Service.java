package shield.shieldbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EC2Service {

    private final String downloadDirectory = "C:/Users/somin/afterML"; // 다운로드된 파일이 저장된 디렉토리 경로

    public String getLatestImageFileNameCam1() {
        String keyword = "cam1";
        // 다운로드된 파일이 저장된 디렉토리에서 특정 키워드를 포함하는 파일 목록을 가져옵니다.
        File[] files = new File(downloadDirectory).listFiles((dir, name) -> name.contains(keyword));

        if (files == null || files.length == 0) {
            return null; // 특정 키워드를 포함하는 파일이 없을 경우 null 반환
        }

        // 파일들을 업로드 시간에 따라 정렬합니다.
        List<File> fileList = Arrays.asList(files);
        fileList.sort(Comparator.comparingLong(File::lastModified).reversed());

        // 가장 최신 파일의 파일명을 반환합니다.
        return fileList.get(0).getName();
    }

    public String getLatestImageFileNameCam2() {
        String keyword = "cam2";
        // 다운로드된 파일이 저장된 디렉토리에서 특정 키워드를 포함하는 파일 목록을 가져옵니다.
        File[] files = new File(downloadDirectory).listFiles((dir, name) -> name.contains(keyword));

        if (files == null || files.length == 0) {
            return null; // 특정 키워드를 포함하는 파일이 없을 경우 null 반환
        }

        // 파일들을 업로드 시간에 따라 정렬합니다.
        List<File> fileList = Arrays.asList(files);
        fileList.sort(Comparator.comparingLong(File::lastModified).reversed());

        // 가장 최신 파일의 파일명을 반환합니다.
        return fileList.get(0).getName();
    }
}
