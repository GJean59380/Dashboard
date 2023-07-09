package com.dashboard.controller;

import com.dashboard.dto.widget.request.CreateWidget;
import com.dashboard.dto.widget.request.UpdateWidget;
import com.dashboard.dto.widget.response.WidgetDto;
import com.dashboard.exception.ResourceNotFoundException;
import com.dashboard.exception.ServiceNameAlreadyExist;
import com.dashboard.exception.WidgetNameAlreadyExist;
import com.dashboard.model.Service;
import com.dashboard.model.Widget;
import com.dashboard.repository.ServiceRepository;
import com.dashboard.repository.WidgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/widget")
public class WidgetController {
    private final WidgetRepository widgetRepository;
    private final ServiceRepository serviceRepository;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public WidgetDto createWidget(@Valid @RequestBody CreateWidget widget) {
        if (widgetRepository.findByName(widget.getName()).isPresent()) {
            throw new ServiceNameAlreadyExist(widget.getName());
        }
        Optional<Service> service = serviceRepository.findById(widget.getServiceId());
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service", "id", widget.getServiceId());
        }
        return widgetRepository.save(widget.dtoToWidget(service.get())).widgetToDto();
    }

    @PatchMapping("/{widgetId}")
    @PreAuthorize("hasRole('ADMIN')")
    public WidgetDto updateWidget(@Valid @RequestBody UpdateWidget widget, @PathVariable(value = "widgetId") Long widgetId) {
        Optional<Widget> optional = widgetRepository.findById(widgetId);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Widget", "id", widgetId);
        }
        Optional<Service> service = serviceRepository.findById(widget.getServiceId());
        if (widget.getServiceId() != null) {
            if (service.isEmpty()) {
                throw new ResourceNotFoundException("Service", "id", widget.getServiceId());
            }
        }
        if (widget.getName() != null) {
            if (widgetRepository.findByName(widget.getName()).isPresent()) {
                throw new WidgetNameAlreadyExist(widget.getName());
            }
        }
        return widgetRepository
                .save(optional.get().dtoToWidget(widget, service.orElseThrow(() -> new ResourceNotFoundException("Service", "id", widget.getServiceId()))))
                .widgetToDto();
    }

    @GetMapping()
    public List<WidgetDto> getWidgets() {
        List<Widget> response = widgetRepository.findAll();
        List<WidgetDto> widgets = new ArrayList<>();
        response.forEach(widget -> widgets.add(new WidgetDto(widget.getId(),widget.getName(), widget.getUrl(), widget.getService().getName(),widget.getActive())));
        return widgets;
    }

    @GetMapping("/{widgetId}")
    public WidgetDto getWidgetsById(@PathVariable(value = "widgetId") Long widgetId) {
        return widgetRepository.findById(widgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Widget", "id", widgetId))
                .widgetToDto();
    }

}
