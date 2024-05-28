package com.realworld.global.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

@Slf4j
@Component
public class FileUtil {

    public String osFilePathSeparation() {
        String osName = System.getProperty("os.name").toLowerCase();
        String rootPath = System.getProperty("user.dir");

        if (osName.contains("mac")) {
            return rootPath + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        } else if (osName.contains("windows")) {
            return rootPath + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        } else if (osName.contains("linux")) {
            return File.separator + "home" + File.separator + "photocard_firebase";
        }

        return File.separator + "home";
    }
}

