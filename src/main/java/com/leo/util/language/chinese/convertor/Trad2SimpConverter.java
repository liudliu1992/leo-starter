package com.leo.util.language.chinese.convertor;

import com.leo.util.language.chinese.convertor.trie.TrieHelper;
import com.leo.util.language.chinese.convertor.trie.TrieNode;

/**
 * 繁体转简体实现
 *
 */
public class Trad2SimpConverter extends AbstractChineseConverter implements ChineseConverter {
  private static final TrieNode ROOT = TrieHelper.build("dict/trad_to_simp_phrases.txt",
          "dict/trad_to_simp_characters.txt");


  @Override
  protected TrieNode getRoot() {
    return ROOT;
  }
}
