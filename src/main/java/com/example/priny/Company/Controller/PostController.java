package com.example.priny.Company.Controller;

import com.example.priny.Company.Dto.PostResponseDTO;
import com.example.priny.Company.Dto.PostSaveRequestDTO;
import com.example.priny.Company.Dto.PostUpdateDTO;
import com.example.priny.Company.SKResponse;
import com.example.priny.Company.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //본인공고조회
    @GetMapping("/post/{id}")
    public ResponseEntity<PostResponseDTO> getJobPostById(@PathVariable Long id) {
        PostResponseDTO jobPost = postService.findById(id);
        return ResponseEntity.ok(jobPost);
    }

    //공고전체조회
    @GetMapping("/listPost")
    public  ResponseEntity<SKResponse>getAllJobPosts() {
        List<PostResponseDTO> jobPosts = postService.findAllJobPosting();
        return ResponseEntity.ok((new SKResponse("success","공고가 등록되었습니다!")));
    }



    //공고 작성
    @PostMapping("/jobPost")
    public ResponseEntity<SKResponse> JobPostSave(@RequestBody PostSaveRequestDTO postSaveRequestDTO){
        HttpHeaders headers = new HttpHeaders();
        postService.JobPostSave(postSaveRequestDTO.getUserId(),postSaveRequestDTO);
        return ResponseEntity.ok((new SKResponse("success","공고가 등록되었습니다!")));
    }


//    //공고 수정
//    @PutMapping("/editPost/{id}")
//    public ResponseEntity<PostResponseDTO> editJobPost(@PathVariable Long id, @RequestBody PostUpdateDTO editDTO) {
//        PostResponseDTO editedJobPost = postService.postedit(id, editDTO);
//        return ResponseEntity.ok(editedJobPost);
//    }


    //공고 삭제
    @DeleteMapping("/deletePost/{id}")
    public  ResponseEntity<SKResponse> deleteJobPost(@PathVariable Long id) {
        postService.deleteById(id);
        return ResponseEntity.ok((new SKResponse("success","공고가 등록되었습니다!")));
    }
}