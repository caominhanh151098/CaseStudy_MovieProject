package com.example.casestudy_movieproject.service.view;

import com.example.casestudy_movieproject.model.View;
import com.example.casestudy_movieproject.repository.MovieRepository;
import com.example.casestudy_movieproject.repository.ViewRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ViewService {
    private final ViewRepository viewRepository;
    private final MovieRepository movieRepository;
    private final HttpSession session;

    public void increaseMovieViews(int idMovie) {
        String sessionId = session.getId();
        View view = viewRepository.findViewByMovie_IdAndSessionId(idMovie, sessionId);
        if (view == null)
            view = new View(movieRepository.findById(idMovie), sessionId, LocalDateTime.now().plusHours(7));
        viewRepository.save(view);
    }
}
