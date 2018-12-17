package com.leo.util.language.chinese.convertor;


import org.junit.Before;
import org.junit.Test;

public class ChineseConverterTest {

    private ChineseConverter converter;

    @Before
    public void instanceChineConvert(){

    }

    @Test
    public void simpleChinese2Trad(){
        converter = new Simp2TradConverter();
        String simple ="克罗托内周中意大利杯客场0比1不敌热那亚，遭遇3连败，3场比赛都没有进球，球队整体状态比较低迷";
        System.out.println(simple);
        System.out.println("***********************************");
        System.out.println(converter.convert(simple));
    }
    @Test
    public void tradChinese2Simple(){
        converter = new Trad2SimpConverter();
        String trad ="沙拿今季在利物浦大爆發，各賽事合計攻入17球，引起部分人把他與蘇亞雷斯比較，後者2014年7月轉投巴塞隆" +
                "拿之前，效力「紅軍」3年半期間轟入82球。\\n修夫基期待卡尼在世界盃發威\\n不過，高普則無意把二人比較：" +
                "「我沒興趣。我十分尊重蘇亞雷斯，他是利物浦的傳奇。阿蘇是十分出色的球員，我喜歡他的態度及比賽風格，" +
                "但他不再是利物浦球員。比較球員不是我的事，那是報紙和社交媒體的故事，我沒興趣。」\\n高普不吝嗇對沙拿狀" +
                "態的讚賞，亦直言此子在利物浦已經幾個月，對英格蘭球壇的新東西也持開放態度，因此不對他的良好狀態感到驚訝：" +
                "「他也試過在高水平（為羅馬）比賽，只是在另一國家，或者對英格蘭人來說，一個球員在某聯賽出色，也可以在" +
                "另一聯賽發光發亮，這件事令他們驚訝。」\\n沙拿的亮眼表現，已經令人預料他是英格蘭PFA足球先生熱門人選，" +
                "但高普認為球季一半都未過，現在有點言之尚早，但也相信該埃及球星如果可以繼續這個水準，他是有機會的。";
        System.out.println(trad);
        System.out.println("***********************************");
        System.out.println(converter.convert(trad));
    }
}