package com.example.casestudy_movieproject.service.vote;

import com.example.casestudy_movieproject.model.User;
import com.example.casestudy_movieproject.model.Vote;
import com.example.casestudy_movieproject.repository.MovieRepository;
import com.example.casestudy_movieproject.repository.UserRepository;
import com.example.casestudy_movieproject.repository.VoteRepository;
import com.example.casestudy_movieproject.service.user.UserService;
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

    public void saveVote(User user, VoteSaveRequest voteRequest) {
        int movieId = Integer.parseInt(voteRequest.getMovieId());
        Vote vote = voteRepository.findByUserAndMovie_id(user, movieId);
        if (vote == null) {
            vote = new Vote();
            vote.setUser(user);
            vote.setMovie(movieRepository.findById(movieId));
        }
        vote.setScore(Integer.parseInt(voteRequest.getScore()));
        voteRepository.save(vote);
    }

    public int getScoreByUserAndMovie(User user, int movieId) {
        Vote vote = voteRepository.findByUserAndMovie_id(user, movieId);
        if (vote == null)
            return getAvgScore(movieId);
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
