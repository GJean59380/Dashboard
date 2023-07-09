package com.dashboard.model;

import com.dashboard.dto.widget.request.UpdateWidget;
import com.dashboard.dto.widget.response.WidgetDto;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "widgets", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Widget {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    @NonNull
    @Size(min = 2)
    private String name;

    @Column
    @Size(min = 2)
    private String url;

    @Column
    private Boolean active=true;


    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    Service service;


    @OneToMany
    private Set<UserWidget> userWidgets;

    @OneToMany
    private Set<Param> params;

    private String description;

    public Widget dtoToWidget(UpdateWidget updateDto, Service service) {
        if (updateDto.getUrl() != null) {
            this.setUrl(updateDto.getUrl());
        }

        if (updateDto.getName() != null) {
            this.setName(updateDto.getName());
        }

        if (updateDto.getServiceId() != null) {
            this.setService(service);
        }

        if (updateDto.getActive() != null) {
            this.setActive(updateDto.getActive());
        }

        return this;
    }



    public WidgetDto widgetToDto() {
        return new WidgetDto(this.getId(),this.getName(), this.getUrl(), this.service.getName(),this.getActive());
    }
}