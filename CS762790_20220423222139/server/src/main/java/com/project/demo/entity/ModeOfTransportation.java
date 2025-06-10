package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *交通方式：(ModeOfTransportation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ModeOfTransportation")
public class ModeOfTransportation implements Serializable {

    //ModeOfTransportation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mode_of_transportation_id")
    private Integer mode_of_transportation_id;
    // 交通方式
    @Basic
    private String mode_of_transportation;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
