对于开闭区间，在数学中的表示方式通常为 () 和 []，小括号代表开放区间，中括号代表封闭区间，而它们的区别主要在于是否包含 = 等于号，开闭区间通常会分为以下一些情形：
```
(1, 10)：代表 1 < x < 10；
(1, 10]：代表 1 < x <= 10；
[1, 10]：代表 1 <= x <= 10；
[1, 10)：代表 1 <= x < 10；
(1, ) 或 (1, ]：代表 x > 1；
[1, ] 或 [1, )：代表 x >= 1；
(, 10) 或 [, 10)：代表 x < 10；
(, 10] 或 [, 10]：代表 x <= 10；
```

那么如何使用Java来判定一个数值是否在指定的开闭区间范围内呢？可以按照以下的思路去实现：

由于开闭区间是有一定规则的，所以开闭区间必须是合法的，除了**(、)、[、]、, 、数字** 之外，其他的字符都是不合法的，所以首先需要校验开闭区间的合法性；
判定开闭区间合法后，再拆分出开闭区间的最小值和最大值；
根据开闭区间是否包含[]，从而在判断时看是否需要添加 = 号；
1、开闭区间的合法性校验
开闭区间的合法性可以通过正则表达式进行验证，具体正则表达式如下：

```
/**
* 开闭区间正则表达式
*/
  private static final Pattern NUM_RANGE_PATTERN = Pattern.compile("[\\[|\\(]\\s?\\d+\\s?,\\s?\\d+\\s?[\\)|\\]]");
```
  左半开闭区间的合法性正则表达式如下：
```
/**
* 左半区间正则表达式
*/
  private static final Pattern LEFT_NUM_RANGE_PATTERN = Pattern.compile("[\\[|\\(]\\s?\\d+\\s?,\\s?[\\)|\\]]");

```

  右半开闭区间的合法性正则表达式如下：
```
/**
* 右半区间正则表达式
  */
  private static final Pattern RIGHT_NUM_RANGE_PATTERN = Pattern.compile("[\\[|\\(],\\s?\\d+\\s?[\\)|\\]]");

```

  判断开闭区间的函数方法代码如下：
```yaml
/**
* 判断是否为有效的数字区间范围
* @param numRange 数字区间
* @return boolean
  */
  public static boolean isValidNumRange(String numRange) {
  return NUM_RANGE_PATTERN.matcher(numRange).matches()
  || LEFT_NUM_RANGE_PATTERN.matcher(numRange).matches()
  || RIGHT_NUM_RANGE_PATTERN.matcher(numRange).matches();
  }
```

  2、判定方法
```
  /**
* 判断数值是否在区间范围内
* @param number 数值
* @param numRange 开闭区间
* @return boolean
  */
  public static boolean inNumRange(int number, String numRange) {
  Objects.requireNonNull(numRange);

  if (!isValidNumRange(numRange)) {
  return false;
  }

  String[] pairs = numRange.split(",");

  // 获取开闭区间的最小值和最大值
  List<String> rangeNums = Arrays.stream(pairs).map(str -> str.replaceAll("[(|)|\\[|\\]]", "").trim()).collect(Collectors.toList());
  Integer minValue = "".equals(rangeNums.get(0)) ? null : Integer.valueOf(rangeNums.get(0));
  Integer maxValue = "".equals(rangeNums.get(1)) ? null : Integer.valueOf(rangeNums.get(1));

  // 判定数值是否大于最小值
  boolean minMatched = (minValue == null) || (pairs[0].startsWith("[") ? number >= minValue : number > minValue);
  // 判定数值是否小于最大值
  boolean maxMatched = (maxValue == null) || (pairs[1].endsWith("]") ? number <= maxValue : number < maxValue);

  return minMatched && maxMatched;
  }

```
  
  3、测试验证
```
  public static void main(String[] args) {
      System.out.println(inNumRange(1, "(0, 2]"));
      System.out.println(inNumRange(1, "(, 2]"));
      System.out.println(inNumRange(1, "(1, 4]"));
      System.out.println(inNumRange(1, "(0, ]"));
  }
```

