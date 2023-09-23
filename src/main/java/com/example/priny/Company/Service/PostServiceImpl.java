package com.example.priny.Company.Service;

import com.example.priny.Company.Domain.Post;
import com.example.priny.Company.Dto.PostResponseDTO;
import com.example.priny.Company.Dto.PostSaveRequestDTO;
import com.example.priny.Company.Dto.PostUpdateDTO;
import com.example.priny.Company.Repository.PostRepository;
import com.example.priny.user.domain.User;
import com.example.priny.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;



    @Override
    public PostResponseDTO findById(Long id) {
        Post Post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("JobPost not found with id: " + id));
        PostResponseDTO PostResponseDTO = postToResponseDTO(Post);
        return PostResponseDTO;
    }


    @Override
    public List<PostResponseDTO> findAllJobPosting() {
        return postRepository.findAll()
                .stream().map(this::postToResponseDTO).collect(Collectors.toList());
    }


    //저장
    @Override
    public Long JobPostSave(String id, PostSaveRequestDTO postSaveRequestDTO) {
        Optional<User> userOptional = userRepository.findByUserId(id);
        User user = userRepository.findByUserId(id).get();
        Post jobPost = postSaveRequestDTO.toEntity();
        jobPost.setUser(user);
        postRepository.save(jobPost);
        return jobPost.getId();
    }


//    @Override
//    public void postedit()


    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
    private PostResponseDTO postToResponseDTO(Post jobPost) {

        return PostResponseDTO.builder()
                .title(jobPost.getTitle())
                .description(jobPost.getDescription())
                .manager(jobPost.getManager())
                .periodList(jobPost.getPeriodList())
                .address(jobPost.getAddress())
                .languageList(jobPost.getLanguageList())
                .build();
    }

}