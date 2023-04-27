package com.example.instagram.module;

import com.example.instagram.module.security.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_photo")
@Getter
@Setter
public class PostPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "directions")
    private String directions;

    @Column(name = "queue")
    private Integer queue;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "created_date")
    private LocalDateTime localDateTime;

    @PrePersist
    private void prePersist(){this.localDateTime = LocalDateTime.now();}

}
