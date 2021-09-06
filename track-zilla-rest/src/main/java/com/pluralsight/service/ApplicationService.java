package com.pluralsight.service;

import com.pluralsight.entity.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    List<Application> listApplications();
    Application findApplication(long id);
}


