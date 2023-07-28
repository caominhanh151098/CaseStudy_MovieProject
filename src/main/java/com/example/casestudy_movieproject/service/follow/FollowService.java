package com.example.casestudy_movieproject.service.follow;

import com.example.casestudy_movieproject.model.Follow;
import com.example.casestudy_movieproject.model.User;
import com.example.casestudy_movieproject.repository.FollowRepository;
import com.example.casestudy_movieproject.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final MovieRepository movieRepository;

    public void likeOrDisLikeMovie(User user, int movieId) {
        Follow follow = checkLikeMovie(user,movieId);
        if (follow == null) {
            follow = new Follow();
            follow.setMovie(movieRepository.findById(movieId));
            follow.setUser(user);
            followRepository.save(follow);
        }
        else {
            followRepository.delete(follow);
        }
    }

    public List<Follow> getListLikeMovie(User user) {
        return followRepository.findAllByUser(user);
    }

    public Follow checkLikeMovie(User user, int movieId) {
        return followRepository.findByMovie_IdAndUser(movieId, user);
    }
}
