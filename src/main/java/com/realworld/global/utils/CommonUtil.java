package com.realworld.global.utils;

import com.realworld.feature.file.controller.FileResponse;
import com.realworld.feature.file.domain.File;
import com.realworld.feature.file.service.FileNameGenerator;
import com.realworld.feature.file.service.StorageService;
import com.realworld.global.code.NickName;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
public class CommonUtil {
    public static boolean isEmpty(Object obj) {
        if (obj == null) return true;
        if ((obj instanceof String) && (((String) obj).trim().length() == 0)) {
            return true;
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        if (obj instanceof List) {
            return ((List<?>) obj).isEmpty();
        }
        if (obj instanceof Object[]) {
            return (((Object[]) obj).length == 0);
        }

        return false;
    }

    public static boolean passwordValidationCheck(String password) {
        return Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", password);
    }

    public static boolean userIdValidationCheck(String userId) {
        return Pattern.matches("^[a-z0-9]{6,12}$", userId);
    }

    public static String createNickname() {
        NickName nickName = new NickName();
        String[] determiners = nickName.getDeterminers();
        int length = determiners.length;
        String determiner = determiners[(int) (Math.random() * length)];
        String[] nicknames = nickName.getName();
        String name = nicknames[(int) (Math.random() * nicknames.length)];
        String nickname = determiner + " " + name;
        log.info(nickname);
        return nickname;
    }


    public static List<FileResponse> upload(User user, List<MultipartFile> multipartFiles, StorageService cloudStorageService) throws IOException {
        List<FileResponse> fileResponseList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {

            String contentType = URLConnection.guessContentTypeFromStream(new BufferedInputStream(multipartFile.getInputStream()));
            if (contentType == null) {
                contentType = multipartFile.getContentType();
            }

            FileNameGenerator fileNameGenerator = new FileNameGenerator();
            String fileName = fileNameGenerator.getMultipartFileName(multipartFile);

            String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

            File file = File.builder()
                    .name(fileName)
                    .size(multipartFile.getSize())
                    .extension(fileExtension)
                    .contentType(contentType)
                    .build();

            try (InputStream inputStream = multipartFile.getInputStream()) {
                File savedFile = cloudStorageService.upload(inputStream, user.getUsername(), file);
                fileResponseList.add(savedFile.toResponse());
            }
        }

        return fileResponseList;
    }
}
