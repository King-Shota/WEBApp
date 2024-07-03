package com.example.demo.service.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.admin.AdminSignupForm;
import com.example.demo.form.admin.GeneralSignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面Service実装クラス
 */
@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {

    /** ユーザー情報テーブルDAO */
    private final UserInfoRepository repository;

    /** PasswordEncoder */
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserInfo> resistAdminUserInfo(AdminSignupForm form) {
        var userInfoExistedOpt = repository.findById(form.getLoginId());
        if (userInfoExistedOpt.isPresent()) {
            return Optional.empty();
        }

        var userInfo = new UserInfo();
        userInfo.setLoginId(form.getLoginId());
        
        var encodedPassword = passwordEncoder.encode(form.getPassword());
        userInfo.setPassword(encodedPassword);
        
        userInfo.setEmail(form.getEmail());
        userInfo.setName(form.getName());
        userInfo.setAuthority(AuthorityKind.ADMIN);
        userInfo.setStatus(form.getStatus());
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setUpdateTime(LocalDateTime.now());

        return Optional.of(repository.save(userInfo));
    }

    @Override
    public Optional<UserInfo> resistGeneralUserInfo(GeneralSignupForm form) {
        var userInfoExistedOpt = repository.findById(form.getLoginId());
        if (userInfoExistedOpt.isPresent()) {
            return Optional.empty();
        }

        var userInfo = new UserInfo();
        userInfo.setLoginId(form.getLoginId());
        
        var encodedPassword = passwordEncoder.encode(form.getPassword());
        userInfo.setPassword(encodedPassword);
        
        userInfo.setEmail(form.getEmail());
        userInfo.setName(form.getName());
        userInfo.setAuthority(AuthorityKind.GENERAL_USER);
        userInfo.setStatus(form.getStatus());
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setUpdateTime(LocalDateTime.now());
        userInfo.setProfileImage(form.getProfileImage().getOriginalFilename());
        userInfo.setFurigana(form.getFurigana());
        userInfo.setGender(form.getGender());
        userInfo.setAge(form.getAge());
        userInfo.setSelfIntroduction(form.getSelfIntroduction());

        return Optional.of(repository.save(userInfo));
    }

    @Override
    public void saveProfileImage(MultipartFile file) throws IOException {
        String uploadDir = "profile-images/";
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }
        Files.copy(file.getInputStream(), Paths.get(uploadDir + file.getOriginalFilename()));
    }
}
