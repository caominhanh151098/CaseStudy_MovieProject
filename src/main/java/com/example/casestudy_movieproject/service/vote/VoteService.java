package com.example.casestudy_movieproject.service.vote;

import com.example.casestudy_movieproject.model.Vote;
import com.example.casestudy_movieproject.repository.MovieRepository;
import com.example.casestudy_movieproject.repository.UserRepository;
import com.example.casestudy_movieproject.repository.VoteRepository;
import com.example.casestudy_movieproject.service.vote.request.VoteSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public void saveVote(VoteSaveRequest voteRequest) {
        int userId = Integer.parseInt(voteRequest.getUserId());
        int movieId = Integer.parseInt(voteRequest.getMovieId());
        Vote vote = voteRepository.findByUser_IdAndMovie_Id(userId, movieId);
        if (vote == null) {
            vote = new Vote();
            vote.setUser(userRepository.findById(userId));
            vote.setMovie(movieRepository.findById(movieId));
        }
        vote.setScore(Integer.parseInt(voteRequest.getScore()));
        voteRepository.save(vote);
    }

    public int getScoreByUserAndMovie(String userId, String movieId) {
        int user = Integer.parseInt(userId);
        int movie = Integer.parseInt(movieId);
        Vote vote = voteRepository.findByUser_IdAndMovie_Id(user, movie);
        if (vote == null)
            return 0;
        else return vote.getScore();
    }

    public int getVoteByMovie(String movieId) {
        return voteRepository.countByMovie_id(Integer.parseInt(movieId));
    }

    public int getAvgScore(int movieId) {
        double avgScore;
        List<Vote> votes = voteRepository.findAllByMovie_Id(movieId);
        avgScore = votes.stream().mapToDouble(Vote::getScore).sum();
        return (int) Math.ceil(avgScore / votes.size());
    }
}
