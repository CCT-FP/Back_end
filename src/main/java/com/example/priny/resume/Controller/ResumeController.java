package com.example.priny.resume.Controller;


import com.example.priny.config.CommonResponse;
import com.example.priny.resume.Entity.ResumeResponseDto;
import com.example.priny.resume.Entity.ResumeSaveRequesDto;
import com.example.priny.resume.Entity.ResumeUpdateDto;
import com.example.priny.resume.Service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Component
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class ResumeController {

    private final ResumeService resumeService;

    @PatchMapping("/resume/{userId}")
    public ResponseEntity<CommonResponse> editResume(@PathVariable String userId, @RequestBody ResumeUpdateDto resumeUpdateDto){
        resumeService.editResume(userId, resumeUpdateDto);
        return ResponseEntity.ok(new CommonResponse("SUCCESS",200));
    }

    @DeleteMapping("/resume/{userId}")
    public ResponseEntity<CommonResponse> deleteResume(@PathVariable String userId){
        resumeService.deleteResume(userId);
        return ResponseEntity.ok(new CommonResponse("SUCCESS",200));
    }


    //이력서 저장
    @PostMapping("/resume")
    public ResponseEntity<CommonResponse> saveResume(@RequestBody ResumeSaveRequesDto resumeSaveRequesDto){
        resumeService.saveResume(resumeSaveRequesDto.getUserId(), resumeSaveRequesDto);
        return ResponseEntity.ok(new CommonResponse("SUCCESS",200));
    }

    //이력서 전체 조회
    @GetMapping("/resumes")
    public List<ResumeResponseDto> resumes(){
        return resumeService.getAllResume();
    }

    //이력서 단일 조회
    @GetMapping("/resume/{userId}")
    public ResumeResponseDto resumeById(@PathVariable String userId){
        return resumeService.getResumeByUserId(userId);
    }



}

