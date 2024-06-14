package com.realworld.feature.signup;

import com.realworld.feature.file.domain.File;
import com.realworld.feature.file.service.FileNameGenerator;
import com.realworld.feature.file.service.StorageService;
import com.realworld.feature.member.controller.request.RegisterMemberRequest;
import com.realworld.feature.member.controller.response.MemberResponse;
import com.realworld.feature.member.domain.Member;
import com.realworld.feature.member.service.MemberCommandService;
import com.realworld.feature.member.service.MemberQueryService;
import com.realworld.global.code.SuccessCode;
import com.realworld.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class SignUpController {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
    private final StorageService cloudStorageService;

    /**
     * 회원 아이디 중복체크
     */
    @GetMapping("/duplication-check/user-id/{user_id}")
    public ResponseEntity<ApiResponse<Boolean>> userIdDuplicationCheck(@PathVariable("user_id") String userId) {
        boolean isExist = memberQueryService.existsByUserId(userId);

        ApiResponse<Boolean> memberApiResponse = new ApiResponse<>(isExist,
                SuccessCode.SELECT_SUCCESS.getStatus(), SuccessCode.SELECT_SUCCESS.getMessage());

        return ResponseEntity.ok(memberApiResponse);
    }

    /**
     * TODO : 로직 정리 필요 너무 더러움..
     * 회원 가입하기
     */
    @Transactional
    @PostMapping("/member")
    public ResponseEntity<ApiResponse<MemberResponse>> signUp(@RequestPart("request") @Valid RegisterMemberRequest request, @RequestPart("multipartFile") MultipartFile multipartFile) throws IOException {

        String contentType = URLConnection.guessContentTypeFromStream(new BufferedInputStream(multipartFile.getInputStream()));

        if (contentType == null) {
            contentType = multipartFile.getContentType();
        }
        FileNameGenerator generator = new FileNameGenerator();

        String fileName = generator.getMultipartFileName(multipartFile);

        String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

        File file = File.builder()
                .name(fileName)
                .size(multipartFile.getSize())
                .extension(fileExtension)
                .contentType(contentType)
                .build();

        UUID fileId = null;

        try (InputStream inputStream = multipartFile.getInputStream()) {
            fileId = cloudStorageService.upload(inputStream, request.getUserId(), file).getId();
        }

        Member member = Member.builder()
                .userId(request.getUserId())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .file(file)
                .userEmail(request.getUserEmail())
                .build();

        Member savedMember = memberCommandService.saveMember(member);


        MemberResponse response = MemberResponse.builder()
                .userId(savedMember.getUserId())
                .phoneNumber(savedMember.getPhoneNumber())
                .nickname(savedMember.getNickname())
                .userEmail(savedMember.getUserEmail())
                .fileId(fileId)
                .build();

        ApiResponse<MemberResponse> memberApiResponse = new ApiResponse<>(response,
                SuccessCode.INSERT_SUCCESS.getStatus(), SuccessCode.INSERT_SUCCESS.getMessage());

        return ResponseEntity.status(HttpStatus.CREATED).body(memberApiResponse);
    }

}
