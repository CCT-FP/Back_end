package com.example.priny.user.Controller;

import com.example.priny.user.DTO.UserDto;
import com.example.priny.user.DTO.UserSignInDto;
import com.example.priny.user.DTO.UserSignInResponseDto;
import com.example.priny.user.Service.UserService;
import com.example.priny.user.Service.UserSignupService;
import com.example.priny.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.Charset;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {

    private final UserSignupService userSignupService;
    private final UserService userService;

    //@CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public Long join(@RequestBody UserDto dto) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text","xml", Charset.forName("UTF-8")));
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Methods","GET,POST,OPTIONS,DELETE,PUT");
        return userSignupService.signUp(dto);
    }
    @PostMapping("/login")
    public UserSignInResponseDto login(@RequestBody UserSignInDto userSignInDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text","xml", Charset.forName("UTF-8")));
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Methods","GET,POST,OPTIONS,DELETE,PUT");
        return userSignupService.login(userSignInDto);
    }


    @GetMapping(value = "/login")
    public String loginUser() {
        return "/User/UserLogin";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", " 아이디 또는 비밀번호를 확인하세요.");
        return "/user/userLogin";
    }


    // 업데이트
    @GetMapping("/update/user")
    public String updateUser(Model model, Authentication authentication) {
        User userDetails = (User) authentication.getPrincipal(); // User 클래스를 사용
        User name = userDetails;
        User userDto = userService.saveUser(name);
        model.addAttribute("user", userDto);

        return "/users/updateUser";
    }

    @PostMapping("/update/user")
    public String updateUser(@Valid UserDto userDto, BindingResult bindingResult, Model model, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDto);
            return "errorPage";
        }
        model.addAttribute("successMessage", "사용자 정보가 업데이트되었습니다.");
        return "succesPage";
    }

    //마이페이지 이동을 위한 사용자 조회
    @GetMapping("/user/{userId}/profile")
    public String myPage(@PathVariable Long userId, Model model) {
        User user = userService.getUserId(userId);
        model.addAttribute("user", user);
        return "profile";
    }
    //회원 탈퇴
    @PostMapping("/delet/user")
    public String memberDelet(@RequestParam String password, Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        boolean result = userService.delet(user.getId(), password);
        if (result){
            return "/logout";
        }
        else{
            model.addAttribute("worronfPassword", "비밀번호가 맞지 않습니다.");
            return "/delet/user";
        }
    }
}

