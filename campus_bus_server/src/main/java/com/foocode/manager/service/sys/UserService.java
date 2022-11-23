package com.foocode.manager.service.sys;


import java.util.Map;

public interface UserService {

    Object getUserList();

    Object getUserListAssociated();

    Object queryUser(Map<String, String> data);

    Object queryUserAssociated(Map<String, String> data);
}
