create table users (
    id bigserial primary key,
    username varchar(255) not null unique,
    password varchar(255) not null,
    is_active boolean not null default false,
    is_non_locked boolean not null default true,
    created_date timestamp not null
);

create table posts (
    id bigserial primary key,
    body text not null,
    like_count bigint not null default 0,
    dislike_count bigint not null default 0,
    user_id bigint not null references users(id),
    created_date timestamp not null
);

create table roles (
    id bigserial primary key,
    title varchar(255) not null unique
);

create table authorities (
    id bigserial primary key,
    title varchar(255) not null unique
);

create table roles_authorities (
    authority_id bigint references authorities(id),
    role_id bigint references roles(id),
    constraint roles_authorities_pk_id primary key (authority_id, role_id)
);

create table users_roles (
    user_id bigint references users(id),
    role_id bigint references roles(id),
    constraint users_roles_pk_id primary key (user_id, role_id)
);

create table post_photos (
    id         bigserial primary key,
    directions varchar(255)  not null,
    queue int not null,
    post_id    bigint not null references posts(id)
);

create table post_likes_dislikes (
    id  bigserial primary key,
    user_id bigint references users(id),
    post_id bigint references posts(id),
    is_like boolean not null,
    created_date timestamp not null
);

create table post_comments (
    id bigserial primary key,
    body text not null,
    post_id bigint not null,
    user_id bigint not null,
    post_comment_id bigint not null,
    foreign key(post_comment_id) references post_comments(id),
    foreign key(post_id) references posts(id),
    foreign key(user_id) references users(id),
    created_date timestamp not null
);

create table subscribers (
    id bigserial primary key,
    user_id bigint not null,
    subscriber_user_id bigint not null,
    unique(user_id, subscriber_user_id),
    foreign key(user_id) references users(id),
    foreign key(subscriber_user_id) references users(id),
    created_date timestamp not null
);