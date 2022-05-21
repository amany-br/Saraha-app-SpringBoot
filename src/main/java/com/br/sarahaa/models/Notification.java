package com.br.sarahaa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notification {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "crated_date")
    private LocalDateTime createdDate;

    private String notification;

    private boolean consulted;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

}

