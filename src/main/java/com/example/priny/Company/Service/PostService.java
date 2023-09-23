package com.example.priny.Company.Service;

import com.example.priny.Company.Dto.PostResponseDTO;
import com.example.priny.Company.Dto.PostSaveRequestDTO;
import com.example.priny.Company.Dto.PostUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostResponseDTO findById(Long id);
    public List<PostResponseDTO> findAllJobPosting();
//    PostResponseDTO postedit(Long id, PostUpdateDTO postUpdateDTO);
    public void deleteById(Long id);
    Long JobPostSave(String userId, PostSaveRequestDTO postSaveRequestDTO);

}
