package com.example.instagram.module;

import com.example.instagram.module.security.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_likes_dislikes")
@Getter
@Setter
public class PostLikeDislike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_like")
    private Boolean isLike;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_date")
    private LocalDateTime localDateTime;

    @PrePersist
    private void prePersist(){this.localDateTime = LocalDateTime.now();}
}
