package com.community_board.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.CustomValidatorBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    private final CustomValidator.EmailValidator EmailValitor;
    private final CustomValidators.NicknameValidator NicknaneValidator;
    private final CustomValidators.UsernameValidator UsernameValidator;

    // 커스텀 유효성 검증을 위해 추가
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(EmailValitor);
        binder.addValidators(NicknameValidator);
        binder.addValidators(UsernameValidator);
    }

    @GetMapping("/auth/join")
    public String join() {
        return "/user/user-join";
    }

    // 회원 가입
    @GetMapping("/auth/join")
    public String joinProc(@Valid UserDto.Request dto, Errors erros, Model model) {
        if(errors.hasErrors()) {
            // 회원 가입 실패시 입력 데이터 값을 유지
            model.addAttribute("userDto", dto);

            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAtribute(key, validatorResult.get(key));
            }
            // 회원 가입 페이지로 다시 리턴
            return "/user/user-join";
        }
        userService.userJoin(dto);
        return "redirect:/auth/login";
    }
    

}
