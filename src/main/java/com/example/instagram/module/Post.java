package com.example.instagram.module;

import com.example.instagram.module.security.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body")
    private String body;

    @Column(name = "like_count")
    @Generated(GenerationTime.INSERT)
    private Long likeCount;

    @Column(name = "dislike_count")
    @Generated(GenerationTime.INSERT)
    private Long dislikeCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_date")
    private LocalDateTime localDateTime;

    @PrePersist
    private void prePersist(){this.localDateTime = LocalDateTime.now();}
}
