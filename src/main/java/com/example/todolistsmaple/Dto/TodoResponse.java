package com.example.todolistsmaple.Dto;

import com.example.todolistsmaple.entity.StatusType;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class TodoResponse {
    private Long id;
    private String content;
    private StatusType status;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
