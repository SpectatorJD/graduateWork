package ru.skypro.homework.service.mappers;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    User usersToDto(UserEntity userEntity);

//    ExtendedAd extendAdToDto(UserEntity userEntity);

    UpdateUser updateUserToDto (UserEntity userEntity);
}

