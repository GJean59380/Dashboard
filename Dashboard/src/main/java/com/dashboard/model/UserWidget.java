package com.dashboard.model;

import com.dashboard.dto.userWidget.UserWidgetDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Setter
@Getter
@Table(name = "user_widget")
public class UserWidget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Widget widget;

    private Integer rank;

    public UserWidgetDto userWidgetToDto(){
        return new UserWidgetDto(this.getWidget().widgetToDto(),this.getRank());
    }
}
