package jp.co.realiine.dao;

import jp.co.realiine.dto.PersonDto;

public interface MyProfileDao {
    PersonDto getMyProfile(PersonDto personDto);
    void setMyProfile(PersonDto personDto);
}
