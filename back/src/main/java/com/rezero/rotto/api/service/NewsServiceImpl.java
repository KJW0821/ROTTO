package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.NewsListDto;
import com.rezero.rotto.dto.response.NewsListResponse;
import com.rezero.rotto.entity.News;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.NewsRepository;
import com.rezero.rotto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private final UserRepository userRepository;
    private final NewsRepository newsRepository;


    // 소식 목록 조회
    public ResponseEntity<?> getNewsList(int userCode) {
        // 해당 유저가 존재하는지 검사
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        // 소식 모두 불러오기
        List<News> allNews = newsRepository.findAll();
        // 최신것부터 보여주기 위해 리스트 뒤집기
        Collections.reverse(allNews);
        // stream 을 통해 allNews 를 순회하며 dto 리스트에 값을 담는다.
        List<NewsListDto> newsList = allNews.stream()
                .map(news -> new NewsListDto(news.getNewsCode(), news.getTitle()))
                .toList();

        // 리스폰스 생성
        NewsListResponse response = new NewsListResponse(newsList);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
