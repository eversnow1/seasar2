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
package org.seasar.extension.jdbc.gen.command;

import java.io.File;
import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.seasar.extension.jdbc.gen.GenerationContext;
import org.seasar.extension.jdbc.gen.command.AbstractCommand.BindableProperty;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import static org.junit.Assert.*;

/**
 * @author taedium
 * 
 */
public class DdlGenCommandTest {

    @Before
    public void setUp() throws Exception {
        SingletonS2ContainerFactory.destroy();
    }

    @After
    public void tearDown() throws Exception {
        SingletonS2ContainerFactory.destroy();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testValidate() throws Exception {
        DdlGenCommand command = new DdlGenCommand();
        command.setDiconFile("s2jdbc-gen-core-test.dicon");
        command.setClasspathRootDir(new File("dir"));
        command.validate();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testFactoryMethod() throws Exception {
        DdlGenCommand command = new DdlGenCommand();
        command.setDiconFile("s2jdbc-gen-core-test.dicon");
        command.setClasspathRootDir(new File("dir"));
        command.validate();
        command.init();
        assertNotNull(command.createEntityMetaReader());
        assertNotNull(command.createGenerator());
        assertNotNull(command.createSchemaModelFactory());
        assertNotNull(command.createTableDescFactory());
        GenerationContext context = command.createGenerationContext(
                new Object(), "aaa.ddl", "ccc.ftl", true);
        assertNotNull(context);
    }

    @Test
    public void testBindableProperty() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(DdlGenCommand.class);
        for (int i = 0; i < beanDesc.getPropertyDescSize(); i++) {
            PropertyDesc propertyDesc = beanDesc.getPropertyDesc(i);
            if (!propertyDesc.hasWriteMethod()) {
                continue;
            }
            Field field = propertyDesc.getField();
            assertTrue(field.isAnnotationPresent(BindableProperty.class));
        }
    }
}
