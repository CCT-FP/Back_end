package com.example.priny.resume.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeUpdateDto {

    private String title;
    private List<String> projectList;
    private String detail;

    @Builder
    public ResumeUpdateDto(String title,  List<String> projectList, String detail){
        this.title = title;
        this.projectList = projectList;
        this.detail = detail;
    }

}
