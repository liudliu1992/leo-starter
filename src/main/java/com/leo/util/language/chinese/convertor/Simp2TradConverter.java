package com.leo.util.language.chinese.convertor;

import com.leo.util.language.chinese.convertor.trie.TrieHelper;
import com.leo.util.language.chinese.convertor.trie.TrieNode;

/**
 * 简体转繁体实现
 *
 */
public class Simp2TradConverter extends AbstractChineseConverter implements ChineseConverter {
  private static final TrieNode ROOT = TrieHelper.build("dict/simp_to_trad_phrases.txt",
          "dict/simp_to_trad_characters.txt");


  @Override
  protected TrieNode getRoot() {
    return ROOT;
  }
}
