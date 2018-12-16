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
public class SearchPersonDaoImpl implements SearchPersonDao {
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
        //String sql = " WHERE id != :my_id";
        String sql = "";
        return sql;
    }

    private List<PersonDto> mapForSearchPersons(List<Map<String, Object>> results) {
        List<PersonDto> personList = new ArrayList<>();

        for (Map<String, Object> r : results) {
            PersonDto person = new PersonDto();

            person.setAppId("appId");
            person.setAppKey("appKey");
            person.setId((Integer)r.get("id"));
            person.setActionIine((Integer)r.get("action_iine"));
            person.setFashionIine((Integer)r.get("fashion_iine"));
            person.setLatitude((BigDecimal) r.get("latitude"));
            person.setLongitude((BigDecimal) r.get("longitude"));
            personList.add(person);
        }
        return personList;
    }

    public List<PersonDto> searchPerson(PersonDto personDto) {
        List<PersonDto> personDtoList;
        try {
            String sql = searchSql() + searchCondition();
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters
                    .addValue("my_id",personDto.getId())
            ;
            List<Map<String, Object>> results = jdbcTemplate.queryForList(sql,parameters);

            logger.info("BotBrainstormDao.searchIdea  get idea num = " + String.valueOf(results.size()));
            personDtoList = mapForSearchPersons(results);

        } catch(Exception e) {
            logger.error("[Exception] SearchPersonDao.searchPerson");
            // throws e;
            return null;
        }
        return personDtoList;
    }

}
