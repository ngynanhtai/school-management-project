package com.project.controller.management;

import com.project.dto.ScoreDTO;
import com.project.dto.common.MessageResponse;
import com.project.dto.common.ResponseDTO;
import com.project.enums.MessageCodeEnum;
import com.project.service.ScoreService;
import com.project.utils.ExceptionUtil;
import com.project.utils.ResponseUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping("/score")
    private ResponseEntity<ResponseDTO> assignScore(@RequestBody ScoreDTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.VALIDATION_REQUEST_NULL);
        }

        return ResponseUtil.buildSuccess(scoreService.assignScore(dto));
    }

    @DeleteMapping("/score/{id}")
    private ResponseEntity<ResponseDTO> deleteScore(@PathVariable("id") Long id,
                                                    @RequestParam("studentId") Long studentId) {
        scoreService.deleteScore(studentId, id);
        return ResponseUtil.buildSuccess(
                MessageResponse
                        .builder()
                        .status(HttpStatus.SC_OK)
                        .message("Delete Score Successfully!")
                        .build());
    }
}
