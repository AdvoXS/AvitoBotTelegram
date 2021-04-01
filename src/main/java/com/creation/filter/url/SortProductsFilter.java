package com.creation.filter.url;

import lombok.Getter;

public class SortProductsFilter extends AttrFilter {
  public enum ValueFilter{
    DEFAULT("101"),
    DATE("104"),
    EXPENSIVE("2"),
    CHEAPER("1");

    @Getter
    public final String value;

    ValueFilter(String value){
      this.value =value;
    }
  }
  @Override
  protected String getUrlAttr() {
    return "s";
  }

  @Override
  protected String getPattern() {
    return "(\\d+)";
  }

}
