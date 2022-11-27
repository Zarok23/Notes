package com.example.notes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "\"notes\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_seq")
    @SequenceGenerator(name = "note_seq", sequenceName = "hibernate_sequence_nt", allocationSize = 1)
    private int id;

    @Column(name = "\"uuid\"", nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "\"header\"", nullable = false)
    @Size(min = 2, message = "Не меньше 2 знаков")
    private String header;

    @Column(name = "\"text\"", nullable = false)
    @Size(min = 1, message = "Не меньше 1 знаков")
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreatedDate
    @Column(name = "\"created\"", updatable = false)
    protected LocalDateTime created = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "\"modified\"")
    protected LocalDateTime modified = LocalDateTime.now();

    public Note(String header, String text, User user) {
        this.header = header;
        this.text = text;
        this.user = user;
    }
}
