package com.dashboard.model;

import com.dashboard.dto.service.request.UpdateService;
import com.dashboard.dto.service.response.ServiceDto;
import lombok.NonNull;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "services", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Service {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    @NonNull
    @Size(min = 2)
    private String name;

    @Column
    @URL
    @NonNull
    private String baseUrl;

    @Column
    private Boolean active = true;

    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    Set<Widget> widgets;

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

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Widget> getWidgets() {
        if (widgets == null) {
            return new HashSet<>();
        }
        return widgets;
    }

    public void setWidgets(Set<Widget> widgets) {
        this.widgets = widgets;
    }

    public Service dtoToService(UpdateService updateDto) {
        if (updateDto.getBaseUrl() != null) {
            this.setBaseUrl(updateDto.getBaseUrl());
        }

        if (updateDto.getName() != null) {
            this.setName(updateDto.getName());
        }

        if (updateDto.getActive() != null) {
            this.setActive(updateDto.getActive());
        }
        return this;
    }

    public ServiceDto serviceToDto() {
        List<String> widgetsNames = new ArrayList<>();
        this.getWidgets().forEach(widget -> widgetsNames.add(widget.getName()));
        return new ServiceDto(this.getId(), this.getName(), this.getBaseUrl(), this.getActive(), widgetsNames);
    }

}
