package com.java.basic.setup.domain.file.repository;


import com.java.basic.setup.domain.file.entity.QFileMetadata;
import com.java.basic.setup.domain.player.entity.QPlayer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileRepositorySupport {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    QFileMetadata qFileMetadata = QFileMetadata.fileMetadata;


}
