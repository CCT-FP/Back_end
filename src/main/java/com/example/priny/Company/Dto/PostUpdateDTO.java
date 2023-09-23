package com.example.priny.Company.Dto;

import com.example.priny.Company.Domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUpdateDTO {
    private String title;
    private String description;
    private String address; //회사 주소
    private String manager; //담당자
    private List<String> periodList;
    private List<String>languageList;//회사 필요로하는 컴퓨터언어


    public Post toEntity(Long id) {
        return Post.builder()
                .title(title)
                .description(description)
                .manager(manager)
                .address(address)
                .periodList(periodList)
                .languageList(languageList)
                .build();
    }

    @Builder
    public PostUpdateDTO(String title, String description, String manager, List<String> periodList, String address, List<String> languageList){
        this.title = title;
        this.description=description;
        this.manager=manager;
        this.address = address;
        this.periodList = periodList;
        this.languageList = languageList;
    }

}