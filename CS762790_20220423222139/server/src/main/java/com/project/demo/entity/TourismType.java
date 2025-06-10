package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *旅游类型：(TourismType)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "TourismType")
public class TourismType implements Serializable {

    //TourismType编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourism_type_id")
    private Integer tourism_type_id;
    // 旅游类型
    @Basic
    private String tourism_type;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
