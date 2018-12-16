package jp.co.realiine.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.realiine.config.ConnectionCert;
import jp.co.realiine.dao.MyProfileDao;
import jp.co.realiine.dao.SearchPersonDao;
import jp.co.realiine.dto.IineDto;
import jp.co.realiine.dto.PersonDto;
import jp.co.realiine.dto.SearchResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RealIineController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MyProfileDao myProfileDao;

    @Autowired
    SearchPersonDao searchPersonDao;

    @Autowired
    ConnectionCert connectionCert;

    @PostMapping("/searchPerson")
    public String searchPerson(@RequestBody PersonDto personDto) {
        logger.info("[IN] RealIineController.searchPerson()");
        ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS);
        String response_json = "";
        if (!connectionCert.check(personDto.getAppId(),personDto.getAppKey())) {
            return "unauthorized connection detected.";
        }
        try {
            logger.info("[--] RealController.searchPerson = " + personDto.createLogString());
            List<PersonDto> personList = searchPersonDao.searchPerson(personDto);
            SearchResponseDto res = new SearchResponseDto();
            res.setItem(personList);
            response_json = mapper.writeValueAsString(res);
        } catch (Exception e) {
            response_json = "Error : searchPerson, e = " + e.getMessage();
        }
        logger.info("[OUT] RealIineController.searchPerson() return:" + response_json);
        return response_json;
    }

    @PostMapping("/setMyProfile")
    public String setMyProfile(@RequestBody PersonDto personDto) {
        logger.info("[IN] RealIineController.setMyProfile()");
        ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS);
        String response_json = "";
        if (!connectionCert.check(personDto.getAppId(),personDto.getAppKey())) {
            return "unauthorized connection detected.";
        }
        try {
            logger.info("[--] RealController.searchPerson = " + personDto.createLogString());
            myProfileDao.setMyProfile(personDto);
            response_json = "OK";
        } catch (Exception e) {
            response_json = "Error : searchPerson, e = " + e.getMessage();
        }
        logger.info("[OUT] RealIineController.setMyProfile()");
        return response_json;
    }

    @PostMapping("/actionIine")
    public String actionIine(@RequestBody IineDto iineDto) {
        logger.info("[IN] RealIineController.actionIine()");
        ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS);
        String response_json = "";
        if (!connectionCert.check(iineDto.getAppId(),iineDto.getAppKey())) {
            return "unauthorized connection detected.";
        }
        try {
            myProfileDao.actionIine(iineDto);
            response_json = "OK";
        } catch (Exception e) {
            response_json = "Error : searchPerson, e = " + e.getMessage();
        }
        logger.info("[OUT] RealIineController.setMyProfile()");
        return response_json;
    }

    @PostMapping("/fashionIine")
    public String fashionIine(@RequestBody IineDto iineDto) {
        logger.info("[IN] RealIineController.actionIine()");
        ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS);
        String response_json = "";
        if (!connectionCert.check(iineDto.getAppId(),iineDto.getAppKey())) {
            return "unauthorized connection detected.";
        }
        try {
            myProfileDao.fasionIine(iineDto);
            response_json = "OK";
        } catch (Exception e) {
            response_json = "Error : searchPerson, e = " + e.getMessage();
        }
        logger.info("[OUT] RealIineController.setMyProfile()");
        return response_json;
    }
}
