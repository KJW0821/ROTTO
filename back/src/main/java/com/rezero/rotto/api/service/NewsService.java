package com.rezero.rotto.api.service;

import org.springframework.http.ResponseEntity;

public interface NewsService {

    ResponseEntity<?> getNewsList(int userCode);

}
