package jp.co.realiine.dto;

import java.util.List;

public class SearchResponseDto {
    List<PersonDto> item;

    public List<PersonDto> getItem() {
        return item;
    }

    public void setItem(List<PersonDto> item) {
        this.item = item;
    }
}
