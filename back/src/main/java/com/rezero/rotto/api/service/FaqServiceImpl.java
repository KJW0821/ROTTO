package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.FaqListDto;
import com.rezero.rotto.dto.response.FaqDetailResponse;
import com.rezero.rotto.dto.response.FaqListResponse;
import com.rezero.rotto.entity.Faq;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.FaqReqository;
import com.rezero.rotto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService{

    private UserRepository userRepository;
    private FaqReqository faqReqository;

    @Override
    public ResponseEntity<?> getFaqList(int userCode, Integer page) {
        User user = userRepository.findByUserCode(userCode);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Faq> faqList = faqReqository.findAll();
        List<FaqListDto> faqListDtos = new ArrayList<>();

        for (int i = 0; i < faqList.size(); i++){
            FaqListDto faqListDto = FaqListDto.builder()
                    .faqCode(faqList.get(i).getFaqCode())
                    .title(faqList.get(i).getTitle())
                    .build();
            faqListDtos.add(faqListDto);
        }

        FaqListResponse faqListResponse = FaqListResponse.builder()
                .faqListDtos(faqListDtos)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(faqListResponse);
    }

    @Override
    public ResponseEntity<?> getFaqDetail(int userCode, int faqCode) {
        User user = userRepository.findByUserCode(userCode);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        Faq faqDetail = faqReqository.findByFaqCode(faqCode);
        FaqDetailResponse faqDetailResponse = FaqDetailResponse.builder()
                .faqCode(faqDetail.getFaqCode())
                .title(faqDetail.getTitle())
                .contents(faqDetail.getContent())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(faqDetailResponse);
    }
}
