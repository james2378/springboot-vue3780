package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *旅游路线：(TouristRoute)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "TouristRoute")
public class TouristRoute implements Serializable {

    //TouristRoute编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourist_route_id")
    private Integer tourist_route_id;
    // 出发地
    @Basic
    private String place_of_departure;
    // 目的地
    @Basic
    private String destination;
    // 交通方式
    @Basic
    private String mode_of_transportation;
    // 旅游类型
    @Basic
    private String tourism_type;
    // 终点风景
    @Basic
    private String destination_scenery;
    // 旅行时间
    @Basic
    private String travel_time;
    // 总体花费
    @Basic
    private String total_cost;
    // 行程路线
    @Basic
    private String travel_route;
    // 旅途详情
    @Basic
    private String travel_details;
    // 点击数
    @Basic
    private Integer hits;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
