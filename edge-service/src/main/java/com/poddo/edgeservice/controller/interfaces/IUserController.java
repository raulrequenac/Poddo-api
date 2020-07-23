package com.poddo.edgeservice.controller.interfaces;

import com.poddo.edgeservice.dto.ChannelUserDto;
import com.poddo.edgeservice.model.User;
import com.poddo.edgeservice.viewModel.UserView;

import java.util.List;

public interface IUserController {
    List<UserView> findAll(String username);
    UserView findByUsername(String username);
    UserView isUser(User user);
    UserView createAdmin(User auth, User user);
    UserView createUser(ChannelUserDto channelUserDto);
    UserView update(User auth, Long id, User user);
    void remove(User auth, Long id);
}
