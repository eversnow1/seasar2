/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.extension.jdbc.operation;

import java.sql.Date;

import junit.framework.TestCase;

import org.seasar.extension.jdbc.Where;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static org.seasar.extension.jdbc.where.EmployeeNames.*;

/**
 * @author koichik
 * 
 */
public class OperationsTest extends TestCase {

    /**
     * 
     */
    public void test() {
        Where w = and(eq(name(), "111"), ne(department().id(), 111),
                isNull(id()), ge(hireDate(), new Date(0)));
        assertEquals(
                "(name = ?) and (department.id <> ?) and (id is null) and (hireDate >= ?)",
                w
                .getCriteria());

        Object[] params = w.getParams();
        assertEquals(3, params.length);
        assertEquals("111", params[0]);
        assertEquals(Integer.valueOf(111), params[1]);
        assertEquals(new Date(0), params[2]);

        String[] names = w.getPropertyNames();
        assertEquals(3, names.length);
        assertEquals("name", names[0]);
        assertEquals("department.id", names[1]);
        assertEquals("hireDate", names[2]);
    }

}