package br.com.erudio.rest_with_spring_boot_and_java_erudio.unittests.mapper;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1.PersonVO;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.mapper.DozerMapper;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.unittests.mapper.mocks.MockPerson;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonDozerConverterTest {
    MockPerson inputObject;

    @BeforeEach
    public void setUp(){
        inputObject = new MockPerson();
    }

    @Test
    public void parseEntityToVOTest(){
        PersonVO output = DozerMapper.parseObject(inputObject.mockEntity(), PersonVO.class);
        TestUtils.validatePerson(output, 0, false);
    }
    @Test
    public void parseEntityListToVOListTest(){
        List<PersonVO> outputList = DozerMapper.parseListObjects(inputObject.mockEntityList(), PersonVO.class);

        TestUtils.validatePerson(outputList.getFirst(), 0, false);
        TestUtils.validatePerson(outputList.get(7), 7, false);
        TestUtils.validatePerson(outputList.get(12), 12, false);
    }
}
