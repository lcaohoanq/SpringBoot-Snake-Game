package com.lcaohoanq.Spring_Snake_Game.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonPropertyOrder({ "id", "last_score", "max_score", "created_at", "updated_at", "user"})
@Entity
public class Score extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id", nullable = false)
    private Long id;

    @Column(name = "last_score", nullable = false)
    private int last_score;

    @Column(name = "max_score", nullable = false)
    private int max_score;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id") // Foreign key column in Score table
    @JsonIgnore
    private User user;


    public Score(int last_score, int max_score, LocalDateTime created_at, LocalDateTime updated_at, User user) {
        super(created_at, updated_at);
        this.last_score = last_score;
        this.max_score = max_score;
        if (user != null) {
            this.id = user.getId();
            this.setUser(user);
        }
    }

}
