package com.example.todolistsmaple.Dto;

import com.example.todolistsmaple.entity.StatusType;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public class TodoRequest {
    private Long id;
    private String content;
    private StatusType status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
