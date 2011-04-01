/*
 * Copyright 2004-2011 the Seasar Foundation and the Others.
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
package examples;

import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import examples.entity.Employee;

/**
 * @author higa
 * 
 */
public class JoinTest extends S2TestCase {

    private JdbcManager jdbcManager;

    protected void setUp() throws Exception {
        include("app.dicon");
    }

    /**
     * @throws Exception
     */
    public void testJoin() throws Exception {
        List<Employee> results =
            jdbcManager
                .from(Employee.class)
                .leftOuterJoin("department")
                .leftOuterJoin("address")
                .getResultList();
        for (Employee e : results) {
            System.out.println(e.name
                + ", "
                + e.department.name
                + ", "
                + e.address.name);
        }
    }
}