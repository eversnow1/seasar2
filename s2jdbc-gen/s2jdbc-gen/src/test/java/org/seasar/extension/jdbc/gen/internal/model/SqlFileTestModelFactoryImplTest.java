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
package org.seasar.extension.jdbc.gen.internal.model;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.seasar.extension.jdbc.gen.model.SqlFileTestModel;
import org.seasar.framework.util.ResourceUtil;

import static org.junit.Assert.*;

/**
 * @author taedium
 * 
 */
public class SqlFileTestModelFactoryImplTest {

    /**
     * 
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        File classpathDir = ResourceUtil.getBuildDir(getClass());
        String basePath = getClass().getPackage().getName().replace(".", "/");
        Set<File> sqlFileSet = new HashSet<File>();
        sqlFileSet.add(ResourceUtil
                .getResourceAsFile(basePath + "/sub/ccc.sql"));
        sqlFileSet.add(ResourceUtil.getResourceAsFile(basePath + "/bbb.sql"));
        sqlFileSet.add(ResourceUtil.getResourceAsFile(basePath + "/aaa.sql"));
        SqlFileTestModelFactoryImpl factory = new SqlFileTestModelFactoryImpl(
                classpathDir, sqlFileSet, "s2jdbc.dicon", "jdbcManager", "hoge");

        SqlFileTestModel model = factory.getSqlFileTestModel();
        assertEquals("s2jdbc.dicon", model.getConfigPath());
        assertEquals("jdbcManager", model.getJdbcManagerName());
        assertEquals("hoge", model.getPackageName());
        assertEquals(3, model.getSqlFilePathList().size());
        assertEquals(basePath + "/aaa.sql", model.getSqlFilePathList().get(0));
        assertEquals(basePath + "/bbb.sql", model.getSqlFilePathList().get(1));
        assertEquals(basePath + "/sub/ccc.sql", model.getSqlFilePathList().get(
                2));
    }
}
