package com.realworld.feature.file.service;


import com.realworld.feature.file.repository.ProductFileRepository;
import com.realworld.infra.aws.AwsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCloudStorageService {
    private final FileNameGenerator fileNameGenerator;
    private final ProductFileRepository productFileRepository;
    private final AwsService awsService;


//    public ProductFileJpaEntity upload(InputStream inputStream, ProductFile file) {
//        file.updateId(fileNameGenerator.createFileId());
//
//        try {
//            if (file.getContentType().contains("image")) {
//                BufferedImage inputImage = ImageIO.read(inputStream);
//
//                try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
//                    ImageIO.write(inputImage, file.getExtension(), os);
//                    try (ByteArrayInputStream ins = new ByteArrayInputStream(os.toByteArray())) {
//                        String imageUrl = awsService.uploadS3Bucket(ins, String.valueOf(file.getId()), os.size(), file.getContentType());
//
//                        file.updateImageUrl(imageUrl);
//                    }
//                }
//            } else {
//                log.error("please upload image file");
//                throw new FileExceptionHandler("이미지 파일만 업로드 해주세요.", ErrorCode.BAD_REQUEST_ERROR);
//            }
//        } catch (IOException e) {
//            log.error("Error create thumbnail image", e);
//            throw new FileExceptionHandler("파일 업로드 중 오류가 발생했습니다.", ErrorCode.BAD_REQUEST_ERROR);
//        }
//
//        log.info("fileEntity:: {}", file.toEntity().toString());
//
//        ProductFileJpaEntity productFileJpaEntity = productFileRepository.save(file.toEntity());
//        log.info("jpaEntity :: {}", productFileJpaEntity);
//        return productFileJpaEntity;
//    }

    public void delete(String userId, String fileId) {

    }
}
