package com.demo.miniapp.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractAuditEntity {

    @Column
    private LocalDateTime createdDateTime;

    @Column
    private String createdBy;

    @Column
    private LocalDateTime updatedDateTime;

    @Column
    private String updatedBy;
}
