package com.creation.filter.url.price;

import com.creation.filter.url.AttrFilter;


public class PriceMaxFilter extends AttrFilter {
  @Override
  protected String getUrlAttr() {
    return "pmax";
  }

  @Override
  protected String getPattern() {
    return "(\\d+)";
  }
}
