package com.example.priny.resume.Service;

import com.example.priny.resume.Entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ResumeService {

    //이력서 저장
    public Long saveResume(String userId, ResumeSaveRequesDto resumeSaveRequesDto);

    //본인 이력서 조회
    public List<ResumeResponseDto> getResumeById(String userId);

    //이력서 전체 조회
    public List<ResumeResponseDto> getAllResume();

    //이력서 수정, 이력서가 존재하지 않을시 IllegalArgumentException
    public void editResume(Long id, ResumeUpdateDto resumeUpdateDto);

    //이력서 삭제
    public void deleteResume(Long id);
    

}
