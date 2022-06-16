package ru.vstu.ueemodule.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUploadingUtils {

    public static String uploadFile(MultipartFile file, String filename, String uploadPath) throws IOException {
        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String uuidPrefix = UUID.randomUUID().toString();
        String resultFilename = uuidPrefix + "." + filename;
        file.transferTo(new File(uploadPath + "/" + resultFilename));
        return resultFilename;
    }

    public static String cyr2lat(char ch) {
        switch (ch) {
            case 'а':
                return "a";
            case 'б':
                return "b";
            case 'в':
                return "v";
            case 'г':
                return "g";
            case 'д':
                return "d";
            case 'е':
                return "e";
            case 'ё':
                return "je";
            case 'ж':
                return "zh";
            case 'з':
                return "z";
            case 'и':
                return "i";
            case 'й':
                return "y";
            case 'к':
                return "k";
            case 'л':
                return "l";
            case 'м':
                return "m";
            case 'н':
                return "n";
            case 'о':
                return "o";
            case 'п':
                return "p";
            case 'р':
                return "r";
            case 'с':
                return "s";
            case 'т':
                return "t";
            case 'у':
                return "u";
            case 'ф':
                return "f";
            case 'х':
                return "kh";
            case 'ц':
                return "c";
            case 'ч':
                return "ch";
            case 'ш':
                return "sh";
            case 'щ':
                return "jsh";
            case 'ъ':
                return "hh";
            case 'ы':
                return "ih";
            case 'ь':
                return "jh";
            case 'э':
                return "eh";
            case 'ю':
                return "ju";
            case 'я':
                return "ja";
            default:
                return String.valueOf(ch);
        }
    }

    public static String cyr2lat(String s) {
        StringBuilder sb = new StringBuilder(s.length() * 2);
        for (char ch : s.toCharArray()) {
            sb.append(cyr2lat(ch));
        }
        return sb.toString();
    }
}
