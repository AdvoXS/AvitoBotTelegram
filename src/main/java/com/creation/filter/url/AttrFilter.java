package com.creation.filter.url;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class AttrFilter implements UrlFilter {

  abstract protected String getUrlAttr();

  abstract protected String getPattern();

  @Override
  public String filterUrl(String replacementUrl, String replaceValue) {
    String urlAttr = getUrlAttr();
    Pattern pattern = Pattern.compile("(.+)(" + urlAttr + "=)" + getPattern()+"(.*)");
    Matcher matcher = pattern.matcher(replacementUrl);
    if (matcher.find()) {
      return matcher.group(1)+ matcher.group(2) + replaceValue+matcher.group(4);
    } else if (replacementUrl.contains("&"))
      return replacementUrl + "&" + urlAttr + "=" + replaceValue;
    else return replacementUrl + "?" + urlAttr + "=" + replaceValue;
  }
}
