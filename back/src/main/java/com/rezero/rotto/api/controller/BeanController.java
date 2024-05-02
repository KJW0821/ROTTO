package com.rezero.rotto.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bean")
@RequiredArgsConstructor
@Tag(name = "Bean 컨트롤러", description = "원두 관리를 위한 API")
public class BeanController {
}
