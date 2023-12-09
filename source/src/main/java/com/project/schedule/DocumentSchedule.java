package com.project.schedule;

import com.project.model.entity.Document;
import com.project.repository.DocumentRepository;
import com.project.utils.ListUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
public class DocumentSchedule {

    @Value("${schedule.document.delete-overdue.config.overdue-days}")
    private int overdueDays;

    @Autowired
    @Lazy
    private DocumentRepository documentRepository;

    @Scheduled(cron = "${schedule.document.delete-overdue.cron}")
    public void deleteOverdueImages() {
        List<Document> documents = documentRepository.findDocumentOverdue(overdueDays).orElse(ListUtil.emptyList());
        log.info("Start Delete Overdue Images, Total: {}", documents.size());

        if (CollectionUtils.isEmpty(documents)) {
            return;
        }

        int count = 0;
        List<String> paths = documents.stream().map(Document::getPath).collect(Collectors.toList());
        for (String path : paths) {
            try {
                Files.delete(Paths.get(path));
                count++;
            } catch (IOException e) {
                log.error("Delete Image: {}. Error: {}", path, e.getMessage());
            }
        }

        log.info("End Delete Overdue Images, Success: {}", String.valueOf(count).concat("/").concat(String.valueOf(documents.size())));
    }
}
