package com.leo.util.language.chinese.convertor;

/**
 *
 * 繁体简体互转抽象
 *
 */
public interface ChineseConverter {

  /**
   * 繁体简体互转
   * @param raw
   * @return
   */
  String convert(String raw);
}
