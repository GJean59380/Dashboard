package com.dashboard.service;

import com.dashboard.dto.user.request.UserUpdate;
import com.dashboard.exception.ResourceNotFoundException;
import com.dashboard.model.User;
import com.dashboard.model.UserWidget;
import com.dashboard.model.Widget;
import com.dashboard.repository.UserWidgetRepository;
import com.dashboard.repository.WidgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class UserService {
    private final WidgetRepository widgetRepository;
    private final UserWidgetRepository userWidgetRepository;

    public User updateUser(UserUpdate userUpdate, User user) {
        AtomicInteger index = new AtomicInteger(0);
        LinkedHashSet<Long> userWidgets = new LinkedHashSet<>();
        user.getWidgetList().forEach(userWidget -> userWidgets.add(userWidget.getId()));
        if (userUpdate.getName() != null) {
            user.setName(userUpdate.getName());
        }
        if (userUpdate.getEmailVerified() != null) {
            user.setEmailVerified(userUpdate.getEmailVerified());
        }

        if (userUpdate.getWidgets() != null) {
            LinkedHashSet<UserWidget> widgets = new LinkedHashSet<>();
            userUpdate.getWidgets().forEach(widget -> {
                Widget findOne = widgetRepository.findById(widget).orElseThrow(()-> new ResourceNotFoundException("Widget","id",widget));
                var us = new UserWidget();
                if (userWidgets.contains(findOne.getId())) {
                    us.setId(findOne.getId());
                }
                us.setWidget(findOne);
                us.setRank(index.get());
                widgets.add(us);
                userWidgetRepository.save(us);
                index.getAndIncrement();
            });


            user.setWidgetList(widgets);
        }
        return user;
    }

}
