package com.java.basic.setup.domain.file.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileMetadata is a Querydsl query type for FileMetadata
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFileMetadata extends EntityPathBase<FileMetadata> {

    private static final long serialVersionUID = -4702882L;

    public static final QFileMetadata fileMetadata = new QFileMetadata("fileMetadata");

    public final StringPath downloadUrl = createString("downloadUrl");

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> uploadDate = createDateTime("uploadDate", java.time.LocalDateTime.class);

    public QFileMetadata(String variable) {
        super(FileMetadata.class, forVariable(variable));
    }

    public QFileMetadata(Path<? extends FileMetadata> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileMetadata(PathMetadata metadata) {
        super(FileMetadata.class, metadata);
    }

}

