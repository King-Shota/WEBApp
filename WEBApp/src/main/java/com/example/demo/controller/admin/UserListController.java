package com.example.demo.controller.admin;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;
import com.example.demo.dto.admin.UserListInfo;
import com.example.demo.form.admin.UserListForm;
import com.example.demo.service.admin.UserListService;

/**
 * コントローラクラス - ユーザーのリスト表示および削除機能を管理します。
 */
@Controller
public class UserListController {

    @Autowired
    private UserListService userListService;

    /**
     * ユーザーリストを表示します。
     *
     * @param model モデルオブジェクト
     * @return ユーザーリスト画面のテンプレート名
     */
    @GetMapping("/userList")
    public String showUserList(Model model) {
        // サービスからユーザーリストを取得
        List<UserListInfo> userList = userListService.editUserList();
        // フォームオブジェクトを初期化
        UserListForm userListForm = new UserListForm();
        
        // AuthorityKind と UserStatusKind のリストをモデルに追加
        List<AuthorityKind> authorityKinds = Arrays.asList(AuthorityKind.values());
        List<UserStatusKind> userStatusKinds = Arrays.asList(UserStatusKind.values());
        model.addAttribute("authorityKinds", authorityKinds);
        model.addAttribute("userStatusKinds", userStatusKinds);
        
        // モデルにユーザーリストとフォームを追加
        model.addAttribute("userList", userList);
        model.addAttribute("userListForm", userListForm);
        return "userList"; // テンプレート名を返す
    }

    /**
     * ユーザー検索を行います。
     *
     * @param model モデルオブジェクト
     * @param form 検索条件を含むフォームオブジェクト
     * @return ユーザーリスト画面のテンプレート名
     */
    @PostMapping("/userList")
    public String searchUsers(Model model, UserListForm form) {
        // サービスから条件に合致するユーザーリストを取得
        List<UserListInfo> userList = userListService.searchUsers(form);
        
        // AuthorityKind と UserStatusKind のリストをモデルに追加
        List<AuthorityKind> authorityKinds = Arrays.asList(AuthorityKind.values());
        List<UserStatusKind> userStatusKinds = Arrays.asList(UserStatusKind.values());
        model.addAttribute("authorityKinds", authorityKinds);
        model.addAttribute("userStatusKinds", userStatusKinds);
        
        // モデルにユーザーリストとフォームを追加
        model.addAttribute("userList", userList);
        model.addAttribute("userListForm", form);
        return "userList"; // テンプレート名を返す
    }

    /**
     * 指定されたログインIDのユーザーを削除します。
     *
     * @param loginId ユーザーのログインID
     * @return ユーザーリスト画面へのリダイレクトURL
     */
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("loginId") String loginId, RedirectAttributes redirectAttributes) {
        try {
            userListService.deleteUser(loginId);
            redirectAttributes.addFlashAttribute("message", "ユーザーが正常に削除されました。");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "ユーザーの削除に失敗しました。");
        }
        return "redirect:/userList";
    }
    
    
}
