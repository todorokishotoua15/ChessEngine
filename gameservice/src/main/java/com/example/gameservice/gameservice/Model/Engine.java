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
@Table(name = "engine", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Engine {
    @Id
    private int id;
    private int depth;
    private int algo;
}
