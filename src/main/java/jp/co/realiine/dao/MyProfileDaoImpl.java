package jp.co.realiine.dao;

import jp.co.realiine.dto.PersonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MyProfileDaoImpl implements MyProfileDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setNamedParameJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String searchSql() {
        String sql = "SELECT id, action_iine, fashion_iine, latitude, longitude FROM realiine ";
        return sql;
    }

    private String searchCondition() {
        String sql = " WHERE id = :my_id";
        return sql;
    }

    private String setProfileSql() {
        String sql = "UPDATE realiine SET " +
                " action_iine = 0, " +
                " fashion_iine = 0, " +
                " latitude = :my_latitude, " +
                " longitude = :my_longitude, " +
                " WHERE id = :my_id ";
        /*
        itemList.add("actionIine:" + String.valueOf(actionIine));
        itemList.add("fashionIine:" + String.valueOf(fashionIine));
        //itemList.add("imageUrl:" + imageUrl);
        itemList.add("latitude:" + String.valueOf(latitude));
        itemList.add("longitude:" + String.valueOf(longitude));
         */
        return sql;
    }

    private List<PersonDto> mapForMyProfiles(List<Map<String, Object>> results) {
        List<PersonDto> personList = new ArrayList<>();

        for (Map<String, Object> r : results) {
            PersonDto person = new PersonDto();

            person.setId((Integer)r.get("id"));
            person.setActionIine((Integer)r.get("action_iine"));
            person.setFashionIine((Integer)r.get("fashion_iine"));
            person.setLatitude((BigDecimal) r.get("latitude"));
            person.setLongitude((BigDecimal) r.get("longitude"));
            personList.add(person);
        }
        return personList;
    }

    public PersonDto getMyProfile(PersonDto personDto) {
        logger.error("[IN] MyProfileDao.getMyProfile");
        List<PersonDto> personDtoList;
        try {
            String sql = searchSql() + searchCondition();
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters
                    .addValue("my_id",personDto.getId())
            ;
            List<Map<String, Object>> results = jdbcTemplate.queryForList(sql,parameters);

            logger.info("BotBrainstormDao.searchIdea  get idea num = " + String.valueOf(results.size()));
            personDtoList = mapForMyProfiles(results);

        } catch(Exception e) {
            logger.error("[Exception] MyProfileDao.getMyProfile");
            // throws e;
            return null;
        }
        logger.error("[OUT] MyProfileDao.getMyProfile");
        return personDtoList.get(0);
    }

    public void setMyProfile(PersonDto personDto) {
        logger.error("[IN] MyProfileDao.setMyProfile");
        List<PersonDto> personDtoList;
        try {
            String sql = setProfileSql();
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters
                    .addValue("my_id",personDto.getId())
                    .addValue("my_latitude",personDto.getLatitude())
                    .addValue("my_longitude",personDto.getLongitude())
            ;
            jdbcTemplate.update(sql,parameters);
        } catch(Exception e) {
            logger.error("[Exception] MyProfileDao.setMyProfile");
            // throws e;
            return;
        }
        logger.error("[OUT] MyProfileDao.setMyProfile");
        return;
    }
}
