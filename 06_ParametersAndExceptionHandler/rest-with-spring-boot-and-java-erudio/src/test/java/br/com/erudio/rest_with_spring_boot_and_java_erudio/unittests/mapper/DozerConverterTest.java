package br.com.erudio.rest_with_spring_boot_and_java_erudio.unittests.mapper;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1.PersonVO;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.mapper.DozerMapper;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DozerConverterTest {
    MockPerson inputObject;

    @BeforeEach
    public void setUp(){
        inputObject = new MockPerson();
    }

    @Test
    public void parseEntityToVOTest(){
        PersonVO output = DozerMapper.parseObject(inputObject.mockEntity(), PersonVO.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name Test: 0", output.getFirstName());
        assertEquals("Last Name Test: 0", output.getLastName());
        assertEquals("Addres Test 0", output.getAddress());
        assertEquals("Male", output.getGender());
    }
    @Test
    public void parseEntityListToVOListTest(){
        List<PersonVO> outputList = DozerMapper.parseListObjects(inputObject.mockEntityList(), PersonVO.class);
        PersonVO outputZero = outputList.getFirst();

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("First Name Test: 0", outputZero.getFirstName());
        assertEquals("Last Name Test: 0", outputZero.getLastName());
        assertEquals("Addres Test 0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());

        PersonVO outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("First Name Test: 7", outputSeven.getFirstName());
        assertEquals("Last Name Test: 7", outputSeven.getLastName());
        assertEquals("Addres Test 7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());

        PersonVO outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("First Name Test: 12", outputTwelve.getFirstName());
        assertEquals("Last Name Test: 12", outputTwelve.getLastName());
        assertEquals("Addres Test 12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
    }
}
