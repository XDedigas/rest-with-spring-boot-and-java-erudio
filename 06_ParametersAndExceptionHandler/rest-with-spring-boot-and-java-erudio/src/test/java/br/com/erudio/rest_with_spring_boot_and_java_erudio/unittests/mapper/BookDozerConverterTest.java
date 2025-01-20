package br.com.erudio.rest_with_spring_boot_and_java_erudio.unittests.mapper;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1.BookVO;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.mapper.DozerMapper;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.unittests.mapper.mocks.MockBook;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookDozerConverterTest {
    MockBook inputObject;

    @BeforeEach
    public void setUp(){
        inputObject = new MockBook();
    }

    @Test
    public void parseEntityToVOTest(){
        Calendar calendar = TestUtils.createCalendar(2025, Calendar.JANUARY, 1);

        BookVO output = DozerMapper.parseObject(inputObject.mockEntity(), BookVO.class);

        TestUtils.validateBook(output, calendar, 0, false);
    }
    @Test
    public void parseEntityListToVOListTest(){
        Calendar calendar = TestUtils.createCalendar(2025, Calendar.JANUARY, 1);

        List<BookVO> outputList = DozerMapper.parseListObjects(inputObject.mockEntityList(), BookVO.class);

        TestUtils.validateBook(outputList.getFirst(), (Calendar) calendar.clone(), 0, false);
        TestUtils.validateBook(outputList.get(7), (Calendar) calendar.clone(), 7, false);
        TestUtils.validateBook(outputList.get(12), calendar, 12, false);
    }
}
