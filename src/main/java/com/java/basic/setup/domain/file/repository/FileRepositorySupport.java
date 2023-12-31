package com.java.basic.setup.domain.file.repository;


import com.java.basic.setup.domain.file.entity.FileMetadata;
import com.java.basic.setup.domain.file.entity.QFileMetadata;
import com.java.basic.setup.domain.player.entity.QPlayer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileRepositorySupport {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    QFileMetadata qFileMetadata = QFileMetadata.fileMetadata;


    // FileRepositorySupport 클래스 내에 추가 메서드
    public List<FileMetadata> findFilesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return queryFactory.selectFrom(qFileMetadata)
                .where(qFileMetadata.uploadDate.between(startDate, endDate))
                .fetch();
    }

}
