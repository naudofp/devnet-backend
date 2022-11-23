package com.naudo.devnet.service.user;

import com.naudo.devnet.dto.user.UserHolderDTO;
import com.naudo.devnet.dto.user.UserLoginDTO;

public interface UserService {

	UserLoginDTO login(UserHolderDTO dto);
}
