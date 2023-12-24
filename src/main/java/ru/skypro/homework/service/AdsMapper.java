package ru.skypro.homework.service;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
@Mapper(componentModel = "spring")
public interface AdsMapper {
    Ad adsToDto(AdEntity adEntity);

    ExtendedAd extendAdToDto(AdEntity adEntity);
}
