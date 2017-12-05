package wx.study;

import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.time.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by fei on 2017/11/28.
 */
public class Jdk8Lambdas {

    public static void main(){

        // Java 8之后：
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));

// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
// 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);


        new Thread( () -> {System.out.print("");
            for (int i = 0; i < 22; i++) {

            }} ).start();




        String separator = ","; //Lambdas会将这些变量隐式得转换成final的
        Arrays.asList( "a", "b", "d" ).forEach(
                ( String e ) -> System.out.print( e + separator ) );



        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
            int result = e1.compareTo( e2 );
            return 111;
        } );



/*      接口可以有默认方法。
@FunctionalInterface
public interface FunctionalDefaultMethods {
    void method();

    default void defaultMethod() {
    }
}*/





        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
//        和 Y一样。。。。
        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
            int result = e1.compareTo( e2 );
            return result;
        } );


//注解改进：在同一个地方不能多次使用同一个注解。Java 8打破了这个限制。。。。（啥几把大事？？？自己去重而已。）





//------------- java8以后的时间。。


        final Clock clock = Clock.systemUTC();
        System.out.println( clock.instant() );//2014-04-12T15:19:29.282Z   //instant是水煎的意思。妈的没动。
        System.out.println( clock.millis() );//1397315969360

        final LocalDate date = LocalDate.now();
        final LocalDate dateFromClock = LocalDate.now( clock );

        System.out.println( date );//2014-04-12    这两种方法都不包含时区
        System.out.println( dateFromClock );//2014-04-12

// Get the local date and local time
        final LocalTime time = LocalTime.now();
        final LocalTime timeFromClock = LocalTime.now( clock );

        System.out.println( time );//11:25:54.568    这两种方法都不包含时区
        System.out.println( timeFromClock );//11:25:54.568


        //LocalDateTime包含日期和时间，但是仍然不包含时区   2014-04-12T11:37:52.309



        //包含时区：
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now( clock );
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now( ZoneId.of( "America/Los_Angeles" ) );
        System.out.println( zonedDatetime );//2014-04-12T11:47:01.017-04:00[America/New_York]
        System.out.println( zonedDatetimeFromClock );//2014-04-12T15:47:01.017Z
        System.out.println( zonedDatetimeFromZone );//2014-04-12T08:47:01.017-07:00[America/Los_Angeles]



        //计算时差。
        final LocalDateTime from = LocalDateTime.of( 2014, Month.APRIL, 16, 0, 0, 0 );
        final LocalDateTime to = LocalDateTime.of( 2015, Month.APRIL, 16, 23, 59, 59 );

        final Duration duration = Duration.between( from, to );
        System.out.println( "Duration in days: " + duration.toDays() );//Duration in days: 365
        System.out.println( "Duration in hours: " + duration.toHours() );//Duration in hours: 8783







        //---------------  默认支持base64。。。。     ----------
        final String text = "Base64 finally in Java 8!";

        final String encoded = Base64
                .getEncoder()
                .encodeToString( text.getBytes( StandardCharsets.UTF_8 ) );
        System.out.println( encoded );//   QmFzZTY0IGZpbmFsbHkgaW4gSmF2YSA4IQ==

        final String decoded = new String(
                Base64.getDecoder().decode( encoded ),
                StandardCharsets.UTF_8 );
        System.out.println( decoded );//Base64 finally in Java 8!
        //http://blog.csdn.net/chszs/article/details/17027649
        /*新的Base64API也支持URL和MINE的编码解码。
(Base64.getUrlEncoder() / Base64.getUrlDecoder(), Base64.getMimeEncoder() / Base64.getMimeDecoder())*/




        //看来，是应该好好看看Arrays类啦。。。。

        long[] ll = new long [ 20000 ];

        Arrays.parallelSetAll( ll,
                index -> ThreadLocalRandom.current().nextInt( 1000000 ) );//随机设置值

        Arrays.stream( ll ).limit( 10 ).forEach(
                i -> System.out.print( i + " " ) );//分页输出,没有排序的。

        System.out.println();

        Arrays.parallelSort( ll );//排序，（高效的。）

        Arrays.stream( ll ).limit( 10 ).forEach(
                i -> System.out.print( i + " " ) );//分页输出,排序的。
        System.out.println();


//Arrays.binarysearch //二分查找



        /*使用Metaspace（JEP 122）代替持久代（PermGen space）。
        在JVM参数方面，使用-XX:MetaSpaceSize和-XX:MaxMetaspaceSize代替原来的-XX:PermSize和-XX:MaxPermSize*/

    }
}
