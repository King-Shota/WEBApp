package com.example.demo.form.admin;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import lombok.Data;



@Data
public class SignupForm {
 
	/** ログインID */
	@Length(min = 8,max = 20)
	private String loginId;
	
	/** パスワード */
	@Length(min = 8,max = 20)
	private String password;
	
	/** メールアドレス */
	@Email
	private String email;
	
	/** お名前 */
	private String name;
	
	
}
