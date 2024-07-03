package com.example.demo.service.admin;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.admin.AdminSignupForm;
import com.example.demo.form.admin.GeneralSignupForm;

/**
 * ユーザー登録画面Serviceインターフェイス
 */
public interface SignupService {

    /**
     * 管理者ユーザー情報を登録する
     *
     * @param form 入力情報
     * @return 登録情報（ユーザー情報Entity）、既に同じユーザIDで登録がある場合はEmpty
     */
    Optional<UserInfo> resistAdminUserInfo(AdminSignupForm form);

    /**
     * 一般ユーザー情報を登録する
     *
     * @param form 入力情報
     * @return 登録情報（ユーザー情報Entity）、既に同じユーザIDで登録がある場合はEmpty
     */
    Optional<UserInfo> resistGeneralUserInfo(GeneralSignupForm form);

    /**
     * プロフィール画像を保存する
     *
     * @param file プロフィール画像ファイル
     */
    void saveProfileImage(MultipartFile file) throws Exception;
}
