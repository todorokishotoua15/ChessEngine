package com.example.gameservice.gameservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "history", uniqueConstraints = { @UniqueConstraint(columnNames = { "history_id" }) })
public class History {
    @Id
    private int history_id;
    private String user_email;
    private int game_id;
}
