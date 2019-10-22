package ru.bytebratsk.bytesvc.service;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import org.springframework.stereotype.Service;

@Service(CurrentUserFullNameService.NAME)
public class CurrentUserFullNameServiceBean implements CurrentUserFullNameService {

    @Override
    public String getCurrentUserFullName() {
        UserSession us = AppBeans.get(UserSessionSource.class).getUserSession();
        User user = us.getCurrentOrSubstitutedUser();

        return user.getLastName() + " " + user.getFirstName() + " " + user.getMiddleName();
    }
}