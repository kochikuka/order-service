package ru.bytebratsk.bytesvc.service;

public interface CurrentUserFullNameService {
    String NAME = "bytesvc_CurrentUserFullNameService";

    String getCurrentUserFullName();
}