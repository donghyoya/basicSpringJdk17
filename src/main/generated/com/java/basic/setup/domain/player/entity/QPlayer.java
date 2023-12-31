package com.java.basic.setup.domain.player.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlayer is a Querydsl query type for Player
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlayer extends EntityPathBase<Player> {

    private static final long serialVersionUID = -1630415911L;

    public static final QPlayer player = new QPlayer("player");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final StringPath userid = createString("userid");

    public QPlayer(String variable) {
        super(Player.class, forVariable(variable));
    }

    public QPlayer(Path<? extends Player> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlayer(PathMetadata metadata) {
        super(Player.class, metadata);
    }

}

