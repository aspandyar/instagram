package com.example.instagram.repository;

import com.example.instagram.module.PostLikeDislike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeDislikeRepository extends JpaRepository<PostLikeDislike, Long> {
}
