package com.creation.filter.url;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegionFilter implements UrlFilter {
  private final Pattern pattern = Pattern.compile("(.*//)([.\\w]*/)(\\w+)(.+)");

  @Override
  public String filterUrl(String replacementUrl, String replaceValue) {
    Matcher m = pattern.matcher(replacementUrl);
    m.matches();
    String output = m.group(1) + m.group(2) + replaceValue + m.group(4);
    return output.toLowerCase().trim();
  }
}
