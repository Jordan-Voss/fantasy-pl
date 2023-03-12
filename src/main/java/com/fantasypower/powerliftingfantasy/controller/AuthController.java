// package com.fantasypower.powerliftingfantasy.auth;
//
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
//
// @RestController
// @RequestMapping("/api/v1")
// @RequiredArgsConstructor
// public class AuthController {
//
// private final LoginService loginService;
//
// @PostMapping("/login")
// public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
// return ResponseEntity.ok(loginService.login(request));
// }
// }
