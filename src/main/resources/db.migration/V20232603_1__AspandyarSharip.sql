create table users (
    id bigserial primary key,
    username varchar(255) not null unique,
    password varchar(255) not null
);

create table posts (
    id bigserial primary key,
    body text not null,
    like_count bigint not null default 0,
    dislike_count bigint not null default 0,
    user_id bigint not null,
    foreign key (user_id) references users(id)
);

create table post_likes_dislikes (
    id bigserial primary key,
    is_like boolean not null,
    post_id bigint not null,
    user_id bigint not null,
    foreign key(post_id) references posts(id),
    foreign key(user_id) references users(id)
);

create table post_comments (
    id bigserial primary key,
    body text not null,
    post_id bigint not null,
    user_id bigint not null,
    post_comment_id bigint not null,
    foreign key(post_comment_id) references post_comments(id),
    foreign key(post_id) references posts(id),
    foreign key(user_id) references users(id)
);

create table subscribers (
    id bigserial primary key,
    user_id bigint not null,
    subscriber_user_id bigint not null,
    foreign key(user_id, subscriber_user_id) references users(id)
);