package com.dashboard.dto.widget.request;

import com.dashboard.model.Service;
import com.dashboard.model.Widget;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;

@RequiredArgsConstructor
@Data
public class CreateWidget {
    private Long id;
    private String name;
    private String url;
    Long serviceId;


    public Widget dtoToWidget(Service service) {
        Widget widget = new Widget();
        widget.setName(this.name);
        widget.setUrl(this.url);
        widget.setService(service);
        widget.setActive(true);
        widget.setUserWidgets(new HashSet<>());
        return widget;
    }

}