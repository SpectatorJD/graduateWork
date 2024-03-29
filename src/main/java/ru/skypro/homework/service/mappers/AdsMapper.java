package ru.skypro.homework.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;


@Mapper(componentModel = "spring")
public interface AdsMapper {

    @Mapping(target = "pk",source = "adEntity.id")
    @Mapping(target = "author",expression = "java(adEntity.getUsers().getId())")
    Ad adsToDto(AdEntity adEntity);

    ExtendedAd extendAdToDto(AdEntity adEntity);

    CreateOrUpdateAd updateAdToDto(AdEntity adEntity);
}
