import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {

    // PNG 파일 시그니처
    private static final byte[] PNG_SIGNATURE = {(byte) 0x89, 'P', 'N', 'G', (byte) 0x0D, (byte) 0x0A, (byte) 0x1A, (byte) 0x0A};

    // 파일이 PNG인지 확인하는 함수
    public static boolean isPngFile(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = new byte[8]; // 첫 8바이트를 읽기 위한 배열
            if (fis.read(bytes) == 8) {
                return java.util.Arrays.equals(PNG_SIGNATURE, bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // 파일 읽기 실패 또는 시그니처 불일치
    }
}