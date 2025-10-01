package com.wedding.article.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;
    private Integer articleId;
    private String subject;
    private String summary;
    private Timestamp writeDateTimestamp;
    private String detailUrl;

    // 기본 생성자
    public Article() {}

    // 생성자
    public Article(Integer articleId, String subject, String summary, Timestamp writeDateTimestamp, String detailUrl) {
        this.articleId = articleId;
        this.subject = subject;
        this.summary = summary;
        this.writeDateTimestamp = writeDateTimestamp;
        this.detailUrl = detailUrl;
    }
}
