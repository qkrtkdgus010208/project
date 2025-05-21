package com.office.library.book.admin.util;

import java.io.File;

import org.springframework.stereotype.Service;

@Service
public class DeleteFileService {

    public boolean delete(String fileName) {
        String uploadDir = "C:/library/upload/";
        File file = new File(uploadDir + fileName);

        if (file.exists()) {
            return file.delete();
        }

        return false;
    }
}
