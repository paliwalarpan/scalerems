package com.scaler.ems.service;

import com.scaler.ems.entities.User;
import com.scaler.ems.model.UserBo;

public interface EmsUserService {

    User save(UserBo userbo);
}
