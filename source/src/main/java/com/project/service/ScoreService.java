package com.project.service;

import com.project.dto.ScoreDTO;

public interface ScoreService {
    ScoreDTO assignScore(ScoreDTO dto);
    void deleteScore(Long studentId, Long id);
}
