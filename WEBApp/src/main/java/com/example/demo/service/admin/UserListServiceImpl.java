package com.example.demo.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;
import com.example.demo.dto.admin.UserListInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.admin.UserListForm;
import com.example.demo.repository.UserInfoRepository;

/**
 * ユーザーリストに関するサービスの実装クラスです。
 */
@Service
public class UserListServiceImpl implements UserListService {

    @Autowired
    private UserInfoRepository repository;

    /**
     * すべてのユーザー情報を取得し、UserListInfoのリストに変換します。
     *
     * @return ユーザーリスト情報のリスト
     */
    @Override
    public List<UserListInfo> editUserList() {
        // リポジトリから全ユーザー情報を取得し、DTOリストに変換
        return toUserListInfos(repository.findAll());
    }

    /**
     * 指定されたログインIDのユーザーを論理削除します。
     *
     * @param loginId ユーザーのログインID
     */
    @Override
    @Transactional
    public void deleteUser(String loginId) {
        // リポジトリを使用してユーザーを論理削除
        repository.softDeleteByLoginId(loginId);
    }

    /**
     * フォームの条件に基づいてユーザーを検索します。
     *
     * @param form 検索条件を含むフォーム
     * @return 検索結果のユーザーリスト情報のリスト
     */
    @Override
    public List<UserListInfo> searchUsers(UserListForm form) {
        // フォームの条件を取得
        String loginId = form.getLoginId();
        UserStatusKind status = form.getUserStatusKind();
        AuthorityKind authority = form.getAuthorityKind();

        // 検索結果を取得
        List<UserInfo> userInfos = repository.searchUsers(loginId, status, authority);
        // DTOリストに変換して返す
        return toUserListInfos(userInfos);
    }

    /**
     * UserInfoエンティティのリストをUserListInfoのリストに変換します。
     *
     * @param userInfos ユーザー情報のエンティティリスト
     * @return ユーザーリスト情報のリスト
     */
    private List<UserListInfo> toUserListInfos(List<UserInfo> userInfos) {
        var userListInfos = new ArrayList<UserListInfo>();
        for (UserInfo userInfo : userInfos) {
            UserListInfo userListInfo = new UserListInfo();
            userListInfo.setLoginId(userInfo.getLoginId());
            userListInfo.setEmail(userInfo.getEmail());
            userListInfo.setName(userInfo.getName());
            userListInfo.setLoginFailureCount(userInfo.getLoginFailureCount());
            userListInfo.setAccountLockedTime(userInfo.getAccountLockedTime());
            userListInfo.setStatus(userInfo.getStatus());
            userListInfo.setAuthority(userInfo.getAuthority());
            userListInfo.setCreateTime(userInfo.getCreateTime());
            userListInfo.setUpdateTime(userInfo.getUpdateTime());
            userListInfos.add(userListInfo);
        }
        return userListInfos;
    }
}
