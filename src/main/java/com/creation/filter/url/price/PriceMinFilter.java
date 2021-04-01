package com.creation.filter.url.price;

import com.creation.filter.url.AttrFilter;


public class PriceMinFilter extends AttrFilter {
  @Override
  protected String getUrlAttr() {
    return "pmin";
  }

  @Override
  protected String getPattern() {
    return "(\\d+)";
  }
}
