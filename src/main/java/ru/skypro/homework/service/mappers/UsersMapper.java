package ru.skypro.homework.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    User usersToDto(UserEntity userEntity);

//    ExtendedAd extendAdToDto(UserEntity userEntity);

    UpdateUser updateUserToDto(UserEntity userEntity);
}

