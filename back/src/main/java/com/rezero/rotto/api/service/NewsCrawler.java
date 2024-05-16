package com.rezero.rotto.api.service;

import com.rezero.rotto.entity.News;
import com.rezero.rotto.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
@RequiredArgsConstructor
public class NewsCrawler {
    
    private final NewsRepository newsRepository;

    @Scheduled(fixedRate = 3600000) // 1시간마다 크롤링 수행
    void crawlCoffeeNews() {
        try {
            // 커피 뉴스 크롤링할 URL
            String url = "https://dailycoffeenews.com/latest-news/";

            // 연결하기
            Document document = Jsoup.connect(url).get();

            // article 요소 선택
            Elements articles = document.select("article");

            // 각 article 요소에 대해 반복
            for (Element article : articles) {
                // News 엔티티 생성
                News news = new News();

                String datePart = null;
                String headlineText = null;

                // 기사 detail 링크 가져오기
                Element link = article.selectFirst("h2 > a");
                if (link != null) {
                    String href = link.attr("href");
                    news.setNewsDetailLink(href);
                } else {
                    continue;
                }

                // 카테고리 가져오기
                Element parentCategory = article.selectFirst(".parent-category");
                if (parentCategory != null) {
                    String categoryText = parentCategory.text();
                    String[] categoryParts = categoryText.split(">");
                    String categoryPart = categoryParts[1].trim();
                    news.setCategory(categoryPart);
                } else {
                    continue;
                }

                // 게시일 가져오기
                Element dateElement = article.selectFirst("p.byline-date");
                if (dateElement != null) {
                    String dateText = dateElement.ownText().trim();
                    String[] dateParts = dateText.split("\\|");
                    datePart = dateParts[2].trim();
                    news.setPostTime(datePart);
                } else {
                    continue;
                }

                // 헤드라인 가져오기
                Element headlineLink = article.selectFirst("h2 > a");
                if (headlineLink != null) {
                    headlineText = headlineLink.text();
                    news.setTitle(headlineText);
                } else {
                    continue;
                }

                // 이미지의 스타일 속성 값 가져오기
                Element imageDiv = article.selectFirst(".blog-featured-bg-image");
                if (imageDiv != null) {
                    String style = imageDiv.attr("style");
                    // 스타일 속성 값에서 이미지 URL을 추출합니다.
                    String imageUrl = extractImageUrlFromStyle(style);
                    news.setImgLink(imageUrl);
                }

                // 작성자 가져오기
                Element authorLink = article.selectFirst("p.byline-date > a");
                if (authorLink != null) {
                    String authorText = authorLink.text();
                    news.setAuthor(authorText);
                }

                // 뉴스 디테일도 추가
                // 커피 뉴스 디테일 URL
                String detailUrl = url + parseDateToUrl(datePart) + headlineText;
                Document detailDocument = Jsoup.connect(detailUrl).get();

                // entry 안에 있는 모든 p 태그들을 하나의 문자열로 합칠 변수
                StringBuilder entryContent = new StringBuilder();

                // 크롤링할 요소 선택
                Elements entry = document.select("entry");

                // entry 에 있는 각 p 태그에 대해 반복
                for (Element paragraph : entry.select("p")) {
                    // p 태그의 텍스트를 가져와서 entryContent에 추가
                    entryContent.append(paragraph.text()).append("\n");
                }

                // News - content 에 넣기
                String combinedText = entryContent.toString();
                news.setContent(combinedText);

                // 데이터 저장
                newsRepository.save(news);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 스타일 속성 값에서 이미지 URL을 추출하는 메서드
    private static String extractImageUrlFromStyle(String style) {
        String imageUrl = "";
        String[] parts = style.split("\\(");
        if (parts.length > 1) {
            String urlPart = parts[1].split("\\)")[0];
            imageUrl = urlPart.replaceAll("'", "").replaceAll("\"", "");
        }
        return imageUrl;
    }

    public static String parseDateToUrl(String dateString) {
        try {
            // 날짜 문자열을 LocalDate 객체로 파싱
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMM dd, yyyy"));

            // 년도와 월 추출
            int year = date.getYear();
            Month month = date.getMonth();
            int monthValue = month.getValue();
            int dayOfMonth = date.getDayOfMonth();

            // detailUrl을 구성하여 반환
            return year + "/" + String.format("%02d", monthValue) + "/" + String.format("%02Dd", dayOfMonth) + "/";
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null; // 파싱 실패 시 null 반환
        }
    }
}
