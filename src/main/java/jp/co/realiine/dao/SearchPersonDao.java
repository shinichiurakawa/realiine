package jp.co.realiine.dao;

import jp.co.realiine.dto.PersonDto;

import java.util.List;

public interface SearchPersonDao {
    List<PersonDto> searchPerson(PersonDto personDto);
}
