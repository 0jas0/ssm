/**
 * Created by wangbo on 16/2/11.
 */
package com.jas.web.utils;

import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类，用于实现一些字符串的常用操作
 */
public class StringUtil extends StringUtils{

    public static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERS = "0123456789";
    public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    public static final String defaultKeyAndValueSeparator = ":";
    public static final String defaultValueEntitySeparator = ",";
    public static final String defaultKeyOrValueQuote = "\"";

    /**
     * 判断字符串是否为空或长度为0
     *
     * @param str
     * @return 若字符串为null或长度为0, 返回true; 否则返回false.
     * <p/>
     * <pre>
     *      isEmpty(null)   =   true;
     *      isEmpty("")     =   true;
     *      isEmpty("  ")   =   false;
     * </pre>
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    /**
     * 判断字符串是否为空或长度为0，或仅由空格组成
     *
     * @param str
     * @return 若字符串为null或长度为0或仅由空格组成, 返回true; 否则返回false.
     * <p/>
     * <pre>
     *      isBlank(null)   =   true;
     *      isBlank("")     =   true;
     *      isBlank("  ")   =   true;
     *      isBlank("MqMsgBody")    =   false;
     *      isBlank("MqMsgBody ")   =   false;
     *      isBlank(" MqMsgBody")   =   false;
     *      isBlank("MqMsgBody b")  =   false;
     * </pre>
     */
    public static boolean isBlank(String str) {
        return (isEmpty(str) || (str.trim().length() == 0));
    }

    /**
     * 将字符串首字母大写后返回
     *
     * @param str 原字符串
     * @return 首字母大写后的字符串
     * <p/>
     * <pre>
     *      capitalizeFirstLetter(null)     =   null;
     *      capitalizeFirstLetter("")       =   "";
     *      capitalizeFirstLetter("1ab")    =   "1ab"
     *      capitalizeFirstLetter("MqMsgBody")      =   "A"
     *      capitalizeFirstLetter("ab")     =   "Ab"
     *      capitalizeFirstLetter("Abc")    =   "Abc"
     * </pre>
     */
    public static String capitalizeFirstLetter(String str) {
        return (isEmpty(str) || !Character.isLetter(str.charAt(0))) ? str : Character.toUpperCase(str.charAt(0))
                + str.substring(1);
    }

    /**
     * 如果不是普通字符，则按照utf8进行编码
     *
     * @param str 原字符
     * @return 编码后字符，编码错误返回null
     * <p/>
     * <pre>
     *      utf8Encode(null)        =   null
     *      utf8Encode("")          =   "";
     *      utf8Encode("aa")        =   "aa";
     *      utf8Encode("啊啊啊啊")   = "编码后字符";
     * </pre>
     */
    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return str;
    }

    /**
     * 如果不是普通字符，则按照utf8进行编码，编码异常则返回defultReturn
     *
     * @param str          源字符串
     * @param defultReturn 默认出错返回
     * @return
     */
    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return defultReturn;
            }
        }
        return str;
    }

    /**
     * null字符串转换为长度为0的字符串
     *
     * @param str 待转换字符串
     * @return
     * @see <pre>
     *  nullStrToEmpty(null)    =   "";
     *  nullStrToEmpty("")      =   "";
     *  nullStrToEmpty("aa")    =   "";
     * </pre>
     */
    public static String nullStrToEmpty(String str) {
        return (str == null ? "" : str);
    }

    /**
     * 得到href链接的innerHtml
     *
     * @param href href内容
     * @return href的innerHtml
     * <ul>
     * <li>空字符串返回""</li>
     * <li>若字符串不为空，且不符合链接正则的返回原内容</li>
     * <li>若字符串不为空，且符合链接正则的返回最后一个innerHtml</li>
     * </ul>
     * @see <pre>
     *      getHrefInnerHtml(null)                                  = ""
     *      getHrefInnerHtml("")                                    = ""
     *      getHrefInnerHtml("mp3")                                 = "mp3";
     *      getHrefInnerHtml("&lt;MqMsgBody innerHtml&lt;/MqMsgBody&gt;")                    = "&lt;MqMsgBody innerHtml&lt;/MqMsgBody&gt;";
     *      getHrefInnerHtml("&lt;MqMsgBody&gt;innerHtml&lt;/MqMsgBody&gt;")                    = "innerHtml";
     *      getHrefInnerHtml("&lt;MqMsgBody&lt;MqMsgBody&gt;innerHtml&lt;/MqMsgBody&gt;")                    = "innerHtml";
     *      getHrefInnerHtml("&lt;MqMsgBody href="baidu.com"&gt;innerHtml&lt;/MqMsgBody&gt;")               = "innerHtml";
     *      getHrefInnerHtml("&lt;MqMsgBody href="baidu.com" title="baidu"&gt;innerHtml&lt;/MqMsgBody&gt;") = "innerHtml";
     *      getHrefInnerHtml("   &lt;MqMsgBody&gt;innerHtml&lt;/MqMsgBody&gt;  ")                           = "innerHtml";
     *      getHrefInnerHtml("&lt;MqMsgBody&gt;innerHtml&lt;/MqMsgBody&gt;&lt;/MqMsgBody&gt;")                      = "innerHtml";
     *      getHrefInnerHtml("jack&lt;MqMsgBody&gt;innerHtml&lt;/MqMsgBody&gt;&lt;/MqMsgBody&gt;")                  = "innerHtml";
     *      getHrefInnerHtml("&lt;MqMsgBody&gt;innerHtml1&lt;/MqMsgBody&gt;&lt;MqMsgBody&gt;innerHtml2&lt;/MqMsgBody&gt;")        = "innerHtml2";
     * </pre>
     */
    public static String getHrefInnerHtml(String href) {
        if (isEmpty(href)) {
            return "";
        }
        // String hrefReg = "[^(<MqMsgBody)]*<[\\s]*MqMsgBody[\\s]*[^(MqMsgBody>)]*>(.+?)<[\\s]*/MqMsgBody[\\s]*>.*";
        String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
        Pattern hrefPattern = Pattern.compile(hrefReg, Pattern.CASE_INSENSITIVE);
        Matcher hrefMatcher = hrefPattern.matcher(href);
        if (hrefMatcher.matches()) {
            return hrefMatcher.group(1);
        }
        return href;
    }

    /**
     * 得到固定长度的随机字符串，字符串由数字和字母混合组成
     *
     * @param length 长度
     * @return
     * @see {@link StringUtil#getRandom(String source, int length)}
     */
    public static String getRandomNumbersAndLetters(int length) {
        return getRandom(NUMBERS_AND_LETTERS, length);
    }

    /**
     * 得到固定长度的随机字符串，字符串由数字混合组成
     *
     * @param length 长度
     * @return
     * @see {@link StringUtil#getRandom(String source, int length)}
     */
    public static String getRandomNumbers(int length) {
        return getRandom(NUMBERS, length);
    }

    /**
     * 得到固定长度的随机字符串，字符串由字母混合组成
     *
     * @param length 长度
     * @return
     * @see {@link StringUtil#getRandom(String source, int length)}
     */
    public static String getRandomLetters(int length) {
        return getRandom(LETTERS, length);
    }

    /**
     * 得到固定长度的随机字符串，字符串由大写字母混合组成
     *
     * @param length 长度
     * @return
     * @see {@link StringUtil#getRandom(String source, int length)}
     */
    public static String getRandomCapitalLetters(int length) {
        return getRandom(CAPITAL_LETTERS, length);
    }

    /**
     * 得到固定长度的随机字符串，字符串由小写字母混合组成
     *
     * @param length 长度
     * @return
     */
    public static String getRandomLowerCaseLetters(int length) {
        return getRandom(LOWER_CASE_LETTERS, length);
    }

    /**
     * 得到固定长度的随机字符串，字符串由source中字符混合组成
     *
     * @param source 源字符串
     * @param length 长度
     * @return <ul>
     * <li>若source为null或为空字符串，返回null</li>
     * <li>否则见{@link StringUtil#getRandom(char[] sourceChar, int length)}</li>
     * </ul>
     */
    public static String getRandom(String source, int length) {
        return StringUtil.isEmpty(source) ? null : getRandom(source.toCharArray(), length);
    }

    /**
     * 得到固定长度的随机字符串，字符串由sourceChar中字符混合组成
     *
     * @param sourceChar 源字符数组
     * @param length     长度
     * @return <ul>
     * <li>若sourceChar为null或长度为0，返回null</li>
     * <li>若length小于0，返回null</li>
     * </ul>
     */
    public static String getRandom(char[] sourceChar, int length) {
        if (sourceChar == null || sourceChar.length == 0 || length < 0) {
            return null;
        }
        StringBuilder str = new StringBuilder("");
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str.append(sourceChar[random.nextInt(sourceChar.length)]);
        }
        return str.toString();
    }

    /**
     * html的转移字符转换成正常的字符串
     *
     * @param source
     * @return
     */
    public static String htmlEscapeCharsToString(String source) {
        if (StringUtil.isEmpty(source)) {
            return "";
        } else {
            return source.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&").replaceAll("&quot;",
                    "\"").replaceAll("&#39;", "'");
        }
    }


    /**
     * 去掉字符串两边的符号，返回去掉后的结果
     *
     * @param source 原字符串
     * @param symbol 符号
     * @return
     */
    public static String RemoveBothSideSymbol(String source, String symbol) {
        if (isEmpty(source) || isEmpty(symbol)) {
            return source;
        }

        int firstIndex = source.indexOf(symbol);
        int lastIndex = source.lastIndexOf(symbol);
        try {
            return source.substring(((firstIndex == 0) ? symbol.length() : 0),
                    ((lastIndex == source.length() - 1) ? (source.length() - symbol.length()) : source.length()));
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }

    /**
     * 重写isNotBlank,将"null"的判断为否
     *
     * @param str 要验证的字符串
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !StringUtils.isEmpty(str) && !(str != null && str.equals("null"));
    }

    /**
     * 字符串匹配(仅支持"*"匹配).
     *
     * @param pattern 模板
     * @param str     要验证的字符串
     * @return
     */
    public static boolean simpleWildcardMatch(String pattern, String str) {
        return wildcardMatch(pattern, str, "*");
    }

    /**
     * 字符串匹配.
     *
     * @param pattern  模板
     * @param str      要验证的字符串
     * @param wildcard 通配符
     * @return
     */
    public static boolean wildcardMatch(String pattern, String str, String wildcard) {
        if (StringUtil.isEmpty(pattern) || StringUtil.isEmpty(str)) {
            return false;
        }
        final boolean startWith = pattern.startsWith(wildcard);
        final boolean endWith = pattern.endsWith(wildcard);
        String[] array = StringUtil.split(pattern, wildcard);
        int currentIndex = -1;
        int lastIndex = -1;
        switch (array.length) {
            case 0:
                return true;
            case 1:
                currentIndex = str.indexOf(array[0]);
                if (startWith && endWith) {
                    return currentIndex >= 0;
                }
                if (startWith) {
                    return currentIndex + array[0].length() == str.length();
                }
                if (endWith) {
                    return currentIndex == 0;
                }
                return str.equals(pattern);
            default:
                for (String part : array) {
                    currentIndex = str.indexOf(part);
                    if (currentIndex > lastIndex) {
                        lastIndex = currentIndex;
                        continue;
                    }
                    return false;
                }
                return true;
        }
    }

    /**
     * 根据后一个字符分割前一个字符
     *
     * @param str   String
     * @param separ String 分隔符
     * @return String[]
     */
    public static String[] separator(String str, String separ) {
        String Str[] = null;
        if (str == null || "".equals(str)) {
            Str = new String[0];
        } else {
            int iStar = 0, iSum = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.substring(i, i + 1).equals(separ)) {
                    iSum++;
                }
            }
            if (!str.substring(str.length() - 1, str.length()).equals(separ)) {
                iSum++;
            }
            Str = new String[iSum];
            iSum = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.substring(i, i + 1).equals(separ)) {
                    Str[iSum] = str.substring(iStar, i).trim();
                    iStar = i + 1;
                    iSum++;
                }
            }
            if (!str.substring(str.length() - 1, str.length()).equals(separ)) {
                Str[iSum] = str.substring(iStar, str.length()).trim();
            }
        }
        return Str;
    }

    /**
     * 截取字符串，多余的字符用省略号代替
     *
     * @param str
     * @param maxLength
     * @return
     */
    public static String subStringCN(final String str, final int maxLength) {
        if (str == null) {
            return str;
        }
        String suffix = "...";
        int suffixLen = suffix.length();

        final StringBuffer sbuffer = new StringBuffer();
        final char[] chr = str.trim().toCharArray();
        int len = 0;
        for (int i = 0; i < chr.length; i++) {

            if (chr[i] >= 0xa1) {
                len += 2;
            } else {
                len++;
            }
        }

        if (len <= maxLength) {
            return str;
        }

        len = 0;
        for (int i = 0; i < chr.length; i++) {

            if (chr[i] >= 0xa1) {
                len += 2;
                if (len + suffixLen > maxLength) {
                    break;
                } else {
                    sbuffer.append(chr[i]);
                }
            } else {
                len++;
                if (len + suffixLen > maxLength) {
                    break;
                } else {
                    sbuffer.append(chr[i]);
                }
            }
        }
        sbuffer.append(suffix);
        return sbuffer.toString();
    }

    /**
     * 判断字符串是否数字
     */
    public static boolean isNumber(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 转换为double
     * 当String支持double时返回double数值否则返回0
     */
    public static double convertToDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /**
     * 验证yearmo格式是否正确
     *
     * @param yearmo
     * @return
     */
    public static boolean validateYearmo(String yearmo) {
        if (StringUtils.isEmpty(yearmo))
            return false;
        return yearmo.matches("^20[0-9]{2}([0][0-9]|[1][0-2])$");
    }

    /**
     * 判断字符串是否整数
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 验证yearmo格式是否正确
     *
     * @param year
     * @return
     */
    public static boolean validateYear(String year) {
        if (StringUtils.isEmpty(year))
            return false;
        return year.matches("^20[0-9]{2}$");
    }




    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c == '_'){
                if (++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 验证是否是HH:mm格式时间
     *
     * @param time
     * @return
     */
    public static boolean validateTime(String time) {
        if (StringUtil.isEmpty(time))
            return false;
        if (time.matches("^\\d{1,2}(:)\\d{1,2}$")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 类似String.split，特性：
     * 1、每个子字符串都会被trim
     * 2、去除trim后为空的子字符串
     * 3、返回不重复的子字符串
     * @param str
     * @param delimiter
     * @return
     */
    public static Set<String> cleanSplit(String str, String delimiter) {
        Set<String> result = new HashSet<String>();
        if (!StringUtils.isEmpty(str)) {
            String[] parts = str.split(delimiter);
            for (String part : parts) {
                part = part.trim();
                if (!StringUtils.isEmpty(part)) {
                    result.add(part);
                }
            }
        }
        return result;
    }


}
