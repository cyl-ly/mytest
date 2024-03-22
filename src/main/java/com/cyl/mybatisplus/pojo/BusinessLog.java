package com.cyl.mybatisplus.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "business_log")
@Data
public class BusinessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "operation_object")
    private String operationObject;

    @Column(name = "operation_result")
    private String operationResult;

    @Column(name = "operation_time")
    private Date operationTime;
}

