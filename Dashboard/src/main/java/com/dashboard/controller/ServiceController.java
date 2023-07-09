package com.dashboard.controller;

import com.dashboard.dto.service.request.CreateService;
import com.dashboard.dto.service.request.UpdateService;
import com.dashboard.dto.service.response.ServiceDto;
import com.dashboard.exception.ResourceNotFoundException;
import com.dashboard.exception.ServiceNameAlreadyExist;
import com.dashboard.model.Service;
import com.dashboard.repository.ServiceRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/service")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceController {
    private final ServiceRepository serviceRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ServiceDto createService(@Valid @RequestBody CreateService createService) {
        Service _service = createService.dtoToService();
        if (serviceRepository.findByName(_service.getName()).isPresent()) {
            throw new ServiceNameAlreadyExist(_service.getName());
        }
        Service service = serviceRepository.save(createService.dtoToService());
        return service.serviceToDto();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{serviceId}")
    public ServiceDto updateService(@Valid @RequestBody UpdateService updateService, @PathVariable(value = "serviceId") Long serviceId) {
        Optional<Service> optional = serviceRepository.findById(serviceId);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Service", "id", serviceId);
        }
        if (updateService.getName() != null) {
            if (serviceRepository.findByName(updateService.getName()).isPresent()) {
                throw new ServiceNameAlreadyExist(updateService.getName());
            }
        }
        Service service = serviceRepository.save(optional.get().dtoToService(updateService));
        return service.serviceToDto();
    }

    @GetMapping()
    public List<ServiceDto> getServices() {
        List<Service> services = serviceRepository.findAll();
        List<ServiceDto> _services = new ArrayList<>();
        services.forEach(service -> {
            _services.add(service.serviceToDto());
        });
        return _services;
    }

    @GetMapping("/{serviceId}")
    public ServiceDto getServiceById(@PathVariable(value = "serviceId") Long serviceId) {
        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "id", serviceId));
        return service.serviceToDto();
    }
}
