package com.kuzank.autobuildcode.common.annotation;

import com.kuzank.autobuildcode.common.enumeration.FieldType;
import com.kuzank.autobuildcode.common.exception.AutoBuildCodeException;
import org.junit.Test;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/17
 */
public class FieldTypeTest {


    @Test
    public void getByJdbcType() throws AutoBuildCodeException {
        System.out.println(FieldType.getByJdbcType("BINARY"));
    }
    
    @Test
    public void getByJdbcTypeNotFound() throws AutoBuildCodeException {
        System.out.println(FieldType.getByJdbcType("BIN"));
    }
}
