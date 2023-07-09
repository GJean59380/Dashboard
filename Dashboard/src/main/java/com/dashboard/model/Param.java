package com.dashboard.model;

import com.dashboard.dto.param.request.ParamDto;

import javax.persistence.*;

@Entity
@Table(name = "param")
public class Param {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String type;
    @ManyToOne
    private Widget widget;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }
    public void setType(String type) {
        this.type = type;
    }
    public ParamDto paramToDto(){
    return new ParamDto(this.name,this.type);
    }
}
