package com.rezero.rotto.api.service;

import com.rezero.rotto.repository.NewsRepository;
import com.rezero.rotto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private final UserRepository userRepository;
    private final NewsRepository newsRepository;

}
