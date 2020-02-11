package src.domain.localdate;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

public class LocalDateTime_Parse_ISO {
    public static void main(String[] args) throws ParseException {
        String dateString ="Sun Apr 27 13:10:02 JST 2014";
        DateTimeFormatter dtf = DateTimeFormatter.ISO_WEEK_DATE;
       // LocalDateTime a = LocalDateTime.parse(dateString,dtf);
         
        
        FastDateFormat dfu =DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT;
        Date a=dfu.parse(dateString);
        System.out.println(a.toString());

    }
}
