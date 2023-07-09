package com.dashboard.controller;

import com.dashboard.dto.about.response.*;
import com.dashboard.dto.param.request.ParamDto;
import com.dashboard.dto.service.request.RequestServiceImpl;
import com.dashboard.model.Widget;
import com.dashboard.repository.ServiceRepository;
import com.dashboard.repository.WidgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/about.json")
public class About {
    private final WidgetRepository widgetRepository;
    private final ServiceRepository serviceRepository;
    private final RequestServiceImpl requestService;

    @GetMapping()
    public AboutJson aboutJson(HttpServletRequest request) {
        Set<Services> services = new HashSet<>();
        serviceRepository.findAll().forEach(service -> {
            Set<Widget> widgets = widgetRepository.findAllByServiceName(service.getName());
            Set<WidgetDtoAbout> widgetDtoAbouts = new HashSet<>();
            widgets.forEach(widget -> {
                Set<ParamDto> paramDto = new HashSet<>();
                widget.getParams().forEach(param -> paramDto.add(param.paramToDto()));
                widgetDtoAbouts.add(new WidgetDtoAbout(widget.getName(), widget.getDescription(), paramDto));
            });

            services.add(new Services(service.getName(), widgetDtoAbouts));

        });
        return new AboutJson(new Client(requestService.getClientIp(request)), new Server(new java.util.Date().getTime(), services));
    }

}
